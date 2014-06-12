package com.jm.timer.background;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jm.constants.CommonConstants;

public class BackgroundProcessing{
	final Log log = LogFactory.getLog(BackgroundProcessing.class);
	private ThreadPoolExecutor mediumPriorityService;
	private ExecutorService lowPriorityService;
	private static BackgroundProcessing backgroundProcessing;

	private BackgroundProcessing() {
	}

	public synchronized static BackgroundProcessing getInstance() {
		if (backgroundProcessing == null) {
			backgroundProcessing = new BackgroundProcessing();
		}
		return backgroundProcessing;
	}

	public void addWorkItem(final WorkItem item) {
		Runnable runnable = new Runnable() {
			public void run() {
				try {
					item.execute();
				} catch (Throwable t) {
					try {
						BackgroundProcessing.this.log.error(
								"Error in work item", t);
					} catch (RuntimeException e) {
						t.printStackTrace();
					}
				}
			}
		};
		if (item.getPriority() == WorkItem.PRIORITY_HIGH) {
			CommonConstants.eventTimer.execute(runnable);
		} else if (item.getPriority() == WorkItem.PRIORITY_MEDIUM) {
			this.mediumPriorityService.execute(runnable);
		} else {
			this.lowPriorityService.execute(runnable);
		}
	}

	public int getMediumPriorityServiceQueueSize() {
		return this.mediumPriorityService.getQueue().size();
	}

	public void initialize() {
		this.mediumPriorityService = new ThreadPoolExecutor(3, 30, 60L,
				TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		this.mediumPriorityService.allowCoreThreadTimeOut(true);
		this.lowPriorityService = Executors.newSingleThreadExecutor();
	}

	public void terminate() {
		this.mediumPriorityService.shutdown();
		this.lowPriorityService.shutdown();
	}

	public void joinTermination() {
		boolean medDone = false;
		boolean lowDone = false;
		try {
			int rewaits = 36;
			while (rewaits > 0) {
				if ((!medDone)
						&& (this.mediumPriorityService.awaitTermination(5L,
								TimeUnit.SECONDS))) {
					medDone = true;
				}
				if ((!lowDone)
						&& (this.lowPriorityService.awaitTermination(5L,
								TimeUnit.SECONDS))) {
					lowDone = true;
				}
				if ((lowDone) && (medDone)) {
					break;
				}
				if ((!lowDone) && (!medDone)) {
					this.log.info("BackgroundProcessing waiting for medium ("
							+ this.mediumPriorityService.getQueue().size()
							+ ") and low priority tasks to complete");
				} else if (!medDone) {
					this.log.info("BackgroundProcessing waiting for medium priority tasks ("
							+ this.mediumPriorityService.getQueue().size()
							+ ") to complete");
				} else {
					this.log.info("BackgroundProcessing waiting for low priority tasks to complete");
				}
				rewaits--;
			}
		} catch (InterruptedException e) {
			this.log.info("", e);
		}
	}
}

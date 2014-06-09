package com.jm.timer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class RealTimeTimer extends AbstractTimer {
	private final TaskQueue queue = new TaskQueue();
	private TimerThread thread;

	public void init(ExecutorService executorService) {
		this.thread = new TimerThread(this.queue, executorService);
		this.thread.setName("ActionNow Timer");
		this.thread.setDaemon(false);
		this.thread.start();
	}

	protected void finalize() {
		synchronized (this.queue) {
			this.thread.newTasksMayBeScheduled = false;
			this.queue.notify();
		}
	}

	public void execute(Runnable command) {
		if (this.thread == null) {
			throw new IllegalStateException("Run init first");
		}
		this.thread.execute(command);
	}

	protected void scheduleImpl(TimerTask task) {
		if (this.thread == null) {
			throw new IllegalStateException("Run init first");
		}
		if ((task.state == TimerTask.CANCELLED)
				|| (task.state == TimerTask.EXECUTED)) {
			throw new IllegalStateException(
					"Task already executed or cancelled");
		}
		synchronized (this.queue) {
			if (!this.thread.newTasksMayBeScheduled) {
				throw new IllegalStateException("Timer already cancelled.");
			}
			synchronized (task.lock) {
				if (task.state == TimerTask.VIRGIN) {
					long time = task.trigger.getFirstExecutionTime();

					if (time < 0L) {
						throw new IllegalArgumentException(
								"Illegal execution time.");
					}
					task.trigger.nextExecutionTime = time;
					task.state = TimerTask.SCHEDULED;
				}
			}

			this.queue.add(task);
			if (this.queue.getMin() == task) {
				this.queue.notify();
			}
		}
	}

	public List<TimerTask> cancel() {
		List<TimerTask> tasks;
		synchronized (this.queue) {
			this.thread.newTasksMayBeScheduled = false;
			tasks = getTasks();
			this.queue.clear();
			this.queue.notify();
		}
		return tasks;
	}

	public ExecutorService getExecutorService() {
		return this.thread.getExecutorService();
	}

	public int purge() {
		int result = 0;

		synchronized (this.queue) {
			for (int i = this.queue.size(); i > 0; i--) {
				if (this.queue.get(i).state == TimerTask.CANCELLED) {
					this.queue.quickRemove(i);
					result++;
				}
			}

			if (result != 0) {
				this.queue.heapify();
			}
		}
		return result;
	}

	public int size() {
		return this.queue.size();
	}

	public List<TimerTask> getTasks() {
		List<TimerTask> result = new ArrayList<TimerTask>();
		synchronized (this.queue) {
			for (int i = 0; i < this.queue.size(); i++) {
				result.add(this.queue.get(i + 1));
			}
		}
		return result;
	}

	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}
}

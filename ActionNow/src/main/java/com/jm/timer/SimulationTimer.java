package com.jm.timer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SimulationTimer extends AbstractTimer {
	private final List<TimerTask> queue = new ArrayList<TimerTask>();
	private boolean cancelled;
	private long currentTime;

	public void setStartTime(long startTime) {
		this.currentTime = startTime;
	}

	public void next() {
		fastForwardTo(this.currentTime + 1L);
	}

	public void fastForwardTo(long time) {
		while ((!this.queue.isEmpty())
				&& (((TimerTask) this.queue.get(0)).trigger.nextExecutionTime <= time)) {
			TimerTask task = (TimerTask) this.queue.get(0);

			this.currentTime = task.trigger.nextExecutionTime;

			if (task.state == TimerTask.CANCELLED) {
				this.queue.remove(0);
			} else {
				long next = task.trigger.calculateNextExecutionTime();
				if (next <= 0L) {
					this.queue.remove(0);
					task.state = TimerTask.EXECUTED;
				} else {
					task.trigger.nextExecutionTime = next;
					updateQueue();
				}

				task.run();
			}
		}

		this.currentTime = time;
	}

	public void execute(Runnable command) {
		command.run();
	}

	protected void scheduleImpl(TimerTask task) {
		if (this.cancelled) {
			throw new IllegalStateException("Timer already cancelled.");
		}
		if ((task.state == TimerTask.CANCELLED) || (task.state == TimerTask.EXECUTED)) {
			throw new IllegalStateException(
					"Task already executed or cancelled");
		}
		if (task.state == TimerTask.VIRGIN) {
			long time = task.trigger.getFirstExecutionTime();
			if (time < 0L) {
				throw new IllegalArgumentException("Illegal execution time.");
			}
			task.trigger.nextExecutionTime = time;
			task.state = TimerTask.SCHEDULED;
		}

		this.queue.add(task);
		updateQueue();
	}

	private void updateQueue() {
		Collections.sort(this.queue, new Comparator<TimerTask>() {
			public int compare(TimerTask t1, TimerTask t2) {
				long diff = t1.trigger.nextExecutionTime
						- t2.trigger.nextExecutionTime;
				if (diff < 0L) {
					return -1;
				}
				if (diff == 0L) {
					return 0;
				}
				return 1;
			}
		});
	}

	public List<TimerTask> cancel() {
		this.cancelled = true;
		List<TimerTask> tasks = getTasks();
		this.queue.clear();
		return tasks;
	}

	public int purge() {
		int result = 0;

		for (int i = this.queue.size(); i > 0; i--) {
			if (((TimerTask) this.queue.get(i)).state == TimerTask.CANCELLED) {
				this.queue.remove(i);
				result++;
			}
		}

		return result;
	}

	public int size() {
		return this.queue.size();
	}

	public List<TimerTask> getTasks() {
		return new ArrayList<TimerTask>(this.queue);
	}

	public long currentTimeMillis() {
		return this.currentTime;
	}
}

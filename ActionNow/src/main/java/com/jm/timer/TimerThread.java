package com.jm.timer;

import java.util.concurrent.ExecutorService;

class TimerThread extends Thread {
	boolean newTasksMayBeScheduled = true;
	private final TaskQueue queue;
	private final ExecutorService executorService;

	TimerThread(TaskQueue queue, ExecutorService executorService) {
		this.queue = queue;
		this.executorService = executorService;
	}

	public void run() {
		try {
			mainLoop();
		} finally {
			synchronized (this.queue) {
				this.newTasksMayBeScheduled = false;
				this.queue.clear();
			}
		}
	}

	void execute(Runnable command) {
		this.executorService.execute(command);
	}

	ExecutorService getExecutorService() {
		return this.executorService;
	}

	private void mainLoop() {
		while (true) {
			try {
				TimerTask task;
				boolean taskFired;
				synchronized (this.queue) {
					if ((this.queue.isEmpty()) && (this.newTasksMayBeScheduled)) {
						this.queue.wait();
						continue;
					}
					if (this.queue.isEmpty()) {
						break;
					}

					task = this.queue.getMin();
					long executionTime;
					synchronized (task.lock) {
						if (task.state == TimerTask.CANCELLED) {
							this.queue.removeMin();
							continue;
						}
						executionTime = task.trigger.nextExecutionTime;
						if ((taskFired = (executionTime <= System
								.currentTimeMillis() ? true : false))) {
							long next = task.trigger
									.calculateNextExecutionTime();
							if (next <= 0L) {
								this.queue.removeMin();
								task.state = TimerTask.EXECUTED;
							} else {
								this.queue.rescheduleMin(next);
							}
						}
					}
					if (!taskFired) {
						long wait = executionTime - System.currentTimeMillis();
						if (wait > 0L) {
							this.queue.wait(wait);
						}
					}
				}
				if (taskFired) {
					this.executorService.execute(task);
				}

				continue;
			} catch (InterruptedException e) {
			}
		}
	}
}

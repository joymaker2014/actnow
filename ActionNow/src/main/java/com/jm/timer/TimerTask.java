package com.jm.timer;


public abstract class TimerTask implements Runnable {
	Object lock = new Object();

	int state = 0;
	static final int VIRGIN = 0;
	static final int SCHEDULED = 1;
	static final int EXECUTED = 2;
	static final int CANCELLED = 3;
	TimerTrigger trigger;
	private final String name;

	public TimerTask(TimerTrigger trigger) {
		this(trigger, null);
	}

	public TimerTask(TimerTrigger trigger, String name) {
		if (trigger == null) {
			throw new NullPointerException();
		}
		this.trigger = trigger;
		this.name = name;
	}

	public boolean cancel() {
		synchronized (this.lock) {
			boolean result = this.state == SCHEDULED;
			this.state = CANCELLED;
			return result;
		}
	}

	protected abstract void run(long paramLong);

	public final void run() {
		long t;
		synchronized (this.lock) {
			t = this.trigger.mostRecentExecutionTime();
		}

		String originalName = null;
		try {
			if (null != this.name && !"".equals(this.name)) {
				originalName = Thread.currentThread().getName();

				Thread.currentThread().setName(
						originalName + " --> " + this.name);
			}

			run(t);
		} finally {
			if (originalName != null) {
				Thread.currentThread().setName(originalName);
			}
		}
	}

	public boolean isCancelled() {
		return this.state == CANCELLED;
	}

	public long getNextExecutionTime() {
		return this.trigger.nextExecutionTime;
	}

	void setTimer(AbstractTimer timer) {
		this.trigger.setTimer(timer);
	}

	AbstractTimer getTimer() {
		return this.trigger.getTimer();
	}
}

package com.jm.timer;

public abstract class TimerTrigger {
	protected AbstractTimer timer;
	long nextExecutionTime;

	void setTimer(AbstractTimer timer) {
		this.timer = timer;
	}

	AbstractTimer getTimer() {
		return this.timer;
	}

	public abstract long mostRecentExecutionTime();

	protected abstract long getFirstExecutionTime();

	public long getNextExecutionTime() {
		return this.nextExecutionTime;
	}

	protected abstract long calculateNextExecutionTime();
}

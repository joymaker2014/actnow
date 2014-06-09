package com.jm.timer;

import java.util.Date;

public abstract class AbstractTimerTrigger extends TimerTrigger {
	private final boolean delayed;
	private final long first;

	public AbstractTimerTrigger(long delay) {
		this.delayed = true;
		this.first = delay;
	}

	public AbstractTimerTrigger(Date start) {
		this.delayed = false;
		this.first = start.getTime();
	}

	protected final long getFirstExecutionTime() {
		if (this.delayed) {
			return this.timer.currentTimeMillis() + this.first;
		}
		return this.first;
	}
}

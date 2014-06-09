package com.jm.timer;

import java.util.Date;

public class FixedDelayTrigger extends AbstractTimerTrigger {
	private final long period;

	public FixedDelayTrigger(long delay, long period) {
		super(delay);
		this.period = period;
	}

	public FixedDelayTrigger(Date start, long period) {
		super(start);
		this.period = period;
	}

	protected long calculateNextExecutionTime() {
		return this.timer.currentTimeMillis() + this.period;
	}

	public long mostRecentExecutionTime() {
		return this.nextExecutionTime - this.period;
	}
}

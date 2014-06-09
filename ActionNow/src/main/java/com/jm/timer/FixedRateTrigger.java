package com.jm.timer;

import java.util.Date;

public class FixedRateTrigger extends AbstractTimerTrigger {
	private final long period;

	public FixedRateTrigger(long delay, long period) {
		super(delay);
		this.period = period;
	}

	public FixedRateTrigger(Date start, long period) {
		super(start);
		this.period = period;
	}

	protected long calculateNextExecutionTime() {
		return this.nextExecutionTime + this.period;
	}

	public long mostRecentExecutionTime() {
		return this.nextExecutionTime - this.period;
	}
}

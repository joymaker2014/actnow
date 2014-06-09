package com.jm.timer;

import java.util.Date;

public class OneTimeTrigger extends AbstractTimerTrigger {
	public OneTimeTrigger(long delay) {
		super(delay);
	}

	public OneTimeTrigger(Date start) {
		super(start);
	}

	protected long calculateNextExecutionTime() {
		return 0L;
	}

	public final long mostRecentExecutionTime() {
		return this.nextExecutionTime;
	}
}

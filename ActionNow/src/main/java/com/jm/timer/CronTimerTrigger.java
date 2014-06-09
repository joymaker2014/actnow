package com.jm.timer;

import java.text.ParseException;
import java.util.Date;

public class CronTimerTrigger extends TimerTrigger {
	private final CronExpression cronExpression;
	private long mostRecent;

	public CronTimerTrigger(String pattern) throws ParseException {
		this.cronExpression = new CronExpression(pattern);
	}

	protected long calculateNextExecutionTime() {
		this.mostRecent = this.nextExecutionTime;
		return this.cronExpression.getNextValidTimeAfter(
				new Date(this.nextExecutionTime)).getTime();
	}

	protected long getFirstExecutionTime() {
		return this.cronExpression.getNextValidTimeAfter(
				new Date(this.timer.currentTimeMillis())).getTime();
	}

	public long mostRecentExecutionTime() {
		return this.mostRecent;
	}
}

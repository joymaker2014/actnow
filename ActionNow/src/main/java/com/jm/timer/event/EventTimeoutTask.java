package com.jm.timer.event;

import com.jm.constants.CommonConstants;
import com.jm.timer.OneTimeTrigger;
import com.jm.timer.TimeoutClient;
import com.jm.timer.TimerTask;
import com.jm.timer.TimerTrigger;

public class EventTimeoutTask extends TimerTask {
	private final TimeoutClient client;

	public EventTimeoutTask(EventTimeoutClient client) {
		this(new OneTimeTrigger(600000L), client);
	}

	public EventTimeoutTask(TimerTrigger trigger, TimeoutClient client) {
		super(trigger);
		this.client = client;
		CommonConstants.eventTimer.schedule(this);
	}

	protected void run(long runtime) {
		this.client.scheduleTimeout(runtime);
	}
}

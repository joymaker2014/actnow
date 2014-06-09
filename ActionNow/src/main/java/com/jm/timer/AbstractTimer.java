package com.jm.timer;

import java.util.List;

public abstract class AbstractTimer {
	public abstract long currentTimeMillis();

	public abstract void execute(Runnable paramRunnable);

	public void execute(Runnable command, String name) {
		if (null == name || "".equals(name)) {
			execute(command);
		} else {
			execute(new NamedRunnable(command, name));
		}
	}

	public final void schedule(TimerTask task) {
		if (task.getTimer() == this) {
			throw new IllegalStateException(
					"Task already scheduled or cancelled");
		}
		task.setTimer(this);
		scheduleImpl(task);
	}

	public void scheduleAll(AbstractTimer that) {
		for (TimerTask task : that.cancel()) {
			schedule(task);
		}
	}

	protected abstract void scheduleImpl(TimerTask paramTimerTask);

	public abstract List<TimerTask> cancel();

	public abstract int purge();

	public abstract int size();

	public abstract List<TimerTask> getTasks();
}

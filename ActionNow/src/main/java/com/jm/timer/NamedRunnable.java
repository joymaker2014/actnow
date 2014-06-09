package com.jm.timer;

public class NamedRunnable implements Runnable {
	private final Runnable runnable;
	private final String name;

	public NamedRunnable(Runnable runnable, String name) {
		this.runnable = runnable;
		this.name = name;
	}

	public void run() {
		String originalName = Thread.currentThread().getName();

		Thread.currentThread().setName(originalName + " --> " + this.name);
		try {
			this.runnable.run();
		} finally {
			Thread.currentThread().setName(originalName);
		}
	}
}

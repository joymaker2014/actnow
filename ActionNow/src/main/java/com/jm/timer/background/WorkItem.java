package com.jm.timer.background;

public abstract interface WorkItem {
	
	public static final int PRIORITY_HIGH = 1;
	public static final int PRIORITY_MEDIUM = 2;
	public static final int PRIORITY_LOW = 3;

	public abstract void execute();

	public abstract int getPriority();
}

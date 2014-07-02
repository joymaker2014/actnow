/**
 * 
 */
package com.jm.constants;

import java.util.concurrent.Executors;

import com.jm.timer.RealTimeTimer;

/**
 * @author LuZheqi
 * 
 */
public class CommonConstants {
	public static final RealTimeTimer eventTimer = new RealTimeTimer();
	public static final RealTimeTimer downloadTimer = new RealTimeTimer();
	public static final RealTimeTimer mixTimer = new RealTimeTimer();

	static {
		eventTimer.init(Executors.newFixedThreadPool(4));
		downloadTimer.init(Executors.newCachedThreadPool());
		mixTimer.init(Executors.newCachedThreadPool());
	}
}

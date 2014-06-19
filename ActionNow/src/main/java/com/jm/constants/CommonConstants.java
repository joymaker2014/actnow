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

	static {
		eventTimer.init(Executors.newFixedThreadPool(4));
	}
}

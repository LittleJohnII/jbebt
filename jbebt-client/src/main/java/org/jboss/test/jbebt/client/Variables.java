/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.client;

/**
 *
 * @author rjanik
 */
public class Variables {
	
	public static final String CONTEST_OPTION = "contest";
	public static final String STATELESS_OPTION = "stateless";
	public static final String STATEFUL_OPTION = "stateful";
	public static final String FAILOVER_OPTION = "failover";
	public static final String REPEAT_OPTION = "repeat";
	public static final String SLEEP_TIME_OPTION = "sleeptime";
	
	public static int repeat = 100; // -x is special value which means: repeat indefinitely
	public static long sleepTime = 0; // in milliseconds
	
}

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
	
	// these strings define the accepted arguments - what the program will do
	// it is important that these options be lowercase, the client relies on this
	public static final String CONTEST = "contest";
	public static final String CONTEST_CREATION = "contest-c";
	public static final String CONTEST_SIMULTANEOUS = "contest-s";
	public static final String CONTEST_CREATION_SIMULTANEOUS = "contest-cs";
	public static final String STATEFUL = "stateful";
	public static final String STATEFUL_C = "stateful-c";
	public static final String STATELESS = "stateless";
	public static final String STATELESS_C = "stateless-c";
	
	// these strings define the accepted arguments which should specify a value
	// as well - how many times will the program do something,...
	public static final String REPEAT = "repeat";
	public static final String SLEEP = "sleep";
	public static final String THREADS = "threads";
	public static final String VERBOSE = "verbose";
	
	// internal variables
	public static int repeat = 100; // must be greater than zero
	public static long sleep = 0; // in milliseconds, must not be lower than zero
	public static int threads = 1; // must be greater than zero, effectively doubles for simultaneous contests
	public static boolean verbose = false; // controls how much output will be generated
	
}

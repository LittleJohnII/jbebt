/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.client;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.jboss.test.jbebt.ejb.RemoteSFSBImpl;

/**
 * Standalone client for remote invocations.
 *
 * @author rjanik
 */
public class Client {
	
	// to execute run: mvn exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath org.jboss.test.jbebt.client.Client stateless repeat=20"
	// or some other combination of arguments that you desire
	
	private final static Logger LOG = Logger.getLogger(RemoteSFSBImpl.class.getName());
	
	private static boolean contest = false;
	private static boolean stateless = false;
	private static boolean stateful = false;
	private static boolean failover = false;

	public static void main(String[] args) throws Exception {
		
		// LOG.setLevel(Level.ALL);
		// ConsoleHandler handler = new ConsoleHandler();
		// handler.setLevel(Level.ALL);
		// handler.setFormatter(new SimpleFormatter());
		// LOG.addHandler(handler);
		
		LOG.info("Starting client...");
		
		// what we will do depends on the arguments we receive
		for (String s : args) {
			if (s.toLowerCase().equals(Variables.CONTEST_OPTION)) {
				LOG.info("Setting contest option to true");
				contest = true;
			} else if (s.toLowerCase().equals(Variables.STATELESS_OPTION)) {
				LOG.info("Setting stateless option to true");
				stateless = true;
			} else if (s.toLowerCase().equals(Variables.STATEFUL_OPTION)) {
				LOG.info("Setting stateful option to true");
				stateful = true;
			} else if (s.toLowerCase().equals(Variables.FAILOVER_OPTION)) {
				LOG.info("Setting failover option to true");
				failover = true;
			} else if (s.toLowerCase().startsWith(Variables.REPEAT_OPTION)) {
				
				// setting repeat
				
				String[] splitS = s.split("=");
				if (splitS.length < 2) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
				try {
					Variables.repeat = Integer.parseInt(splitS[1]);
				} catch (NumberFormatException ex) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
			} else if (s.toLowerCase().startsWith(Variables.SLEEP_TIME_OPTION)) {
				
				// setting sleep time
				
				String[] splitS = s.split("=");
				if (splitS.length < 2) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
				try {
					long sleepTime = Long.parseLong(splitS[1]);
					if (sleepTime < 0) {
						throw new NumberFormatException();
					}
					Variables.sleepTime = sleepTime;
				} catch (NumberFormatException ex) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
			} else {
				LOG.log(Level.INFO, "Unknown parameter: {0}", s);
				System.err.println("Unknown parameter: " + s);
				return;
			}
		}
		
		if (contest) {
			LOG.info("Starting contest...");
			LOG.info("Calling invokeStatefulRepeatedly...");
			Util.invokeStatefulRepeatedly();
			LOG.info("Calling invokeStatelessRepeatedly...");
			Util.invokeStatelessRepeatedly();
		} else if (stateful) {
			LOG.info("Calling invokeStatefulRepeatedly...");
			Util.invokeStatefulRepeatedly();
		} else if (stateless) {
			LOG.info("Calling invokeStatelessRepeatedly...");
			Util.invokeStatelessRepeatedly();
		} else if (failover) {
			LOG.info("Calling testStatefulFailover...");
			Util.testStatefulFailover();
		}

	}
	
}

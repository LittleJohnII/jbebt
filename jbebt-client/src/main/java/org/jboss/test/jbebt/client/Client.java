package org.jboss.test.jbebt.client;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jboss.test.jbebt.ejb.RemoteSFSBImpl;

/**
 * Standalone client for remote invocations. To execute the application run
 * mvn exec:exec -Dexec.executable="java" -Dexec.args="-classpath %classpath org.jboss.test.jbebt.client.Client stateless repeat=20"
 * or some other combination of arguments instead of "stateless repeat=20".
 * To find out what arguments are supported consult readme or run the application
 * with "help" argument. (Or anything else that is not supported :))
 *
 * @author rjanik
 */
public class Client {
	
	private final static Logger LOG = Logger.getLogger(RemoteSFSBImpl.class.getName());
	
	private static boolean contest = false;
	private static boolean contestSimultaneous = false;
	private static boolean contestCreation = false;
	private static boolean contestCreationSimultaneous = false;
	private static boolean stateful = false;
	private static boolean statefulCreation = false;
	private static boolean stateless = false;
	private static boolean statelessCreation = false;

	public static void main(String[] args) throws Exception {
		
		spacing();
		
		LOG.info("Starting client...");
		
		// what we will do depends on the arguments we receive
		// this section of the client does nothing else except parsing the
		// arguments and setting up the program
		for (String s : args) {
			if (s.toLowerCase().equals(Variables.CONTEST)) {
				LOG.info("Setting contest option to true");
				contest = true;
			} else if (s.toLowerCase().equals(Variables.CONTEST_SIMULTANEOUS)) {
				LOG.info("Setting simultaneous contest option to true");
				contestSimultaneous = true;
			} else if (s.toLowerCase().equals(Variables.CONTEST_CREATION)) {
				LOG.info("Setting creation contest option to true");
				contestCreation = true;
			} else if (s.toLowerCase().equals(Variables.CONTEST_CREATION_SIMULTANEOUS)) {
				LOG.info("Setting simultaneous creation contest option to true");
				contestCreationSimultaneous = true;
			} else if (s.toLowerCase().equals(Variables.STATEFUL)) {
				LOG.info("Setting stateful option to true");
				stateful = true;
			} else if (s.toLowerCase().equals(Variables.STATEFUL_C)) {
				LOG.info("Setting stateful creation option to true");
				statefulCreation = true;
			} else if (s.toLowerCase().equals(Variables.STATELESS)) {
				LOG.info("Setting stateless option to true");
				stateless = true;
			} else if (s.toLowerCase().equals(Variables.STATELESS_C)) {
				LOG.info("Setting stateless creation option to true");
				statelessCreation = true;
			} else if (s.toLowerCase().equals(Variables.VERBOSE)) {
				LOG.info("Setting verbose option to true");
				Variables.verbose = true;
			} else if (s.toLowerCase().startsWith(Variables.REPEAT)) {
				
				// setting repeat
				LOG.info("Setting repeat option");
				
				String[] splitS = s.split("=");
				if (splitS.length < 2) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
				try {
					Variables.repeat = Integer.parseInt(splitS[1].trim());
				} catch (NumberFormatException ex) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
			} else if (s.toLowerCase().startsWith(Variables.SLEEP)) {
				
				// setting sleep time
				LOG.info("Setting sleep time option");
				
				String[] splitS = s.split("=");
				if (splitS.length < 2) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
				try {
					long sleepTime = Long.parseLong(splitS[1].trim());
					if (sleepTime < 0) {
						throw new NumberFormatException();
					}
					Variables.sleep = sleepTime;
				} catch (NumberFormatException ex) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
			} else if (s.toLowerCase().startsWith(Variables.THREADS)) {
				
				// setting threads
				LOG.info("Setting threads option");
				
				String[] splitS = s.split("=");
				if (splitS.length < 2) {
					LOG.log(Level.INFO, "Wrong value for: {0}", s);
					System.err.println("Wrong value for: " + s);
					return;
				}
				
				try {
					int threads = Integer.parseInt(splitS[1].trim());
					if (threads <= 0) {
						throw new NumberFormatException();
					}
					Variables.threads = threads;
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
		
		// we will now create a thread for each one defined in Variables.threads
		// each thread will carry out the same action
		for (int i = 0; i < Variables.threads; i++) {
			if (contest) {
				System.out.println("Starting contest...");
				Util.ContestRunner contestRunner = new Util.ContestRunner();
				new Thread(contestRunner).start();
			} else if (contestSimultaneous) {
				System.out.println("Starting simultaneus contest...");
				Util.StatefulRunner statefulRunner = new Util.StatefulRunner();
				Util.StatelessRunner statelessRunner = new Util.StatelessRunner();
				new Thread(statefulRunner).start();
				new Thread(statelessRunner).start();
			} else if (contestCreation) {
				System.out.println("Starting creation contest...");
				Util.CreationContestRunner creationContestRunner = new Util.CreationContestRunner();
				new Thread(creationContestRunner).start();
			} else if (contestCreationSimultaneous) {
				System.out.println("Starting simultaneous creation contest...");
				Util.StatefulCreationRunner statefulCreationRunner = new Util.StatefulCreationRunner();
				Util.StatelessCreationRunner statelessCreationRunner = new Util.StatelessCreationRunner();
				new Thread(statefulCreationRunner).start();
				new Thread(statelessCreationRunner).start();
			} else if (stateful) {
				System.out.println(Thread.currentThread().getId() + ": Starting stateful invocations...");
				Util.StatefulRunner statefulRunner = new Util.StatefulRunner();
				new Thread(statefulRunner).start();
			} else if (statefulCreation) {
				System.out.println(Thread.currentThread().getId() + ": Starting stateful creations...");
				Util.StatefulCreationRunner statefulCreationRunner = new Util.StatefulCreationRunner();
				new Thread(statefulCreationRunner).start();
			} else if (stateless) {
				System.out.println(Thread.currentThread().getId() + ": Starting stateless invocations...");
				Util.StatelessRunner statelessRunner = new Util.StatelessRunner();
				new Thread(statelessRunner).start();
			} else if (statelessCreation) {
				System.out.println(Thread.currentThread().getId() + ": Starting stateless creations...");
				Util.StatelessCreationRunner statelessCreationRunner = new Util.StatelessCreationRunner();
				new Thread(statelessCreationRunner).start();
			} else {
				System.out.println("Printing usage...");
				usage();
			}
		}
		
		spacing();

	}
	
	private static void usage() {
		System.out.println("To run this program type:");
		System.out.println("\n\tmvn exec:exec -Dexec.executable=\"java\" -Dexec.args=\"-classpath %classpath org.jboss.test.jbebt.client.Client [arguments]\"\n");
		System.out.println("where possible [arguments] are:");
		System.out.println("\tcontest     \t\tinvokes creation of [repeat] number of stateful and then stateless beans and measures elapsed time");
		System.out.println("\tcreation    \t\tcounts to [repeat] with stateful and then stateless beans and measures elapsed time");
		System.out.println("\tcontest-s   \t\tinvokes creation of [repeat] number of stateful and then stateless beans and measures elapsed time, this option is simultaneous: threads=1 is default");
		System.out.println("\tcreation-s  \t\tcounts to [repeat] with stateful and then stateless beans and measures elapsed time, this option is simultaneous: threads=1 is default");
		System.out.println("\tstateless   \t\tcounts to [repeat] with stateless beans and measures elapsed time");
		System.out.println("\tstateful    \t\tcounts to [repeat] with stateful beans and measures elapsed time, good for testing failover (with long sleeptime)");
		System.out.println("\tstateless-c \t\tinvokes creation of [repeat] number of stateless beans and measures elapsed time");
		System.out.println("\tstateful-c  \t\tinvokes creation of [repeat] number of stateful beans and measures elapsed time");
		System.out.println("\trepeat=x    \t\tdefines how many times will an action be repeated, should be > 0");
		System.out.println("\tsleeptime=x \t\tdefines how long a delay between two actions should be, in miliseconds, 0 - no delay, should be >= 0");
		System.out.println("\tthreads=x   \t\tdefines how many threads will be used to generate the load, to get the number of total actions invoked, multiply [repeat] * [threads] or 2 * [repeat] * [threads] for contest and creation options");
		System.out.println("\tverbose     \t\tswitches the program into verbose mode - some more output will be generated - good for debug");
		System.out.println("Note that you should always specify exactly one of the following options: contest, creation, contest-s, creation-s, stateless, statefull, stateless-c, stateful-c");
	}
	
	private static void spacing() {
		System.out.println("\n#############################################\n");
	}
	
}

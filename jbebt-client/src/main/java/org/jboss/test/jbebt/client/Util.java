package org.jboss.test.jbebt.client;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.test.jbebt.ejb.RemoteSFSB;
import org.jboss.test.jbebt.ejb.RemoteSLSB;

/**
 * Class containing static methods for bean lookup, invocations and possibly
 * other utilities.
 *
 * @author rjanik
 */
public class Util {
	
	/**
	 * Looks up remote stateless session bean.
	 * 
	 * @return RemoteSLSB - A remote stateless session bean which can be used for subsequent invocations
	 * @throws NamingException 
	 */
	public static RemoteSLSB lookupRemoteSLSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSLSBImpl!" + RemoteSLSB.class.getName();
		
		return (RemoteSLSB) context.lookup(ejbID);
	}
	
	/**
	 * Looks up a remote stateful session bean.
	 * 
	 * @return RemoteSFSB - A remote stateful session bean which can be used for subsequent invocations
	 * @throws NamingException 
	 */
	public static RemoteSFSB lookupRemoteSFSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSFSBImpl!" + RemoteSFSB.class.getName() + "?stateful";
		
		return (RemoteSFSB) context.lookup(ejbID);
		
	}
	
	/**
	 * Looks up a remote stateless session bean and invokes method getNodeName() on it.
	 * 
	 * @param runNumber - int - which run is this method being executed for
	 * @throws NamingException 
	 */
	public static void invokeStatelessBean(int runNumber) throws NamingException {
		
		final RemoteSLSB slb = lookupRemoteSLSB();
		if (Variables.verbose) {
			System.out.println("Obtained a remote stateless bean for invocation number " + runNumber);
		}

		String node = slb.getNodeName();
		if (Variables.verbose) {
			System.out.println("node: " + node);
		}
        
	}
	
	/**
	 * Looks up a remote stateful session bean and invokes method getNodeName() on it.
	 * 
	 * @param runNumber - int - which run is this method being executed for
	 * @throws NamingException
	 * @throws InterruptedException 
	 */
	public static void invokeStatefulBean(int runNumber) throws NamingException, InterruptedException {
		
		final RemoteSFSB sfb = lookupRemoteSFSB();
		if (Variables.verbose) {
			System.out.println("Obtained a remote stateful bean for invocation number " + runNumber);
		}

		String node = sfb.getNodeName();
		if (Variables.verbose) {
			System.out.println("node: " + node);
		}
        
	}
	
	/**
	 * Calls method Util.invokeStatelessBean in loop for as many times as is specified
	 * in Variables.repeat and measures the elapsed time.
	 * 
	 * @throws NamingException
	 * @throws InterruptedException 
	 */
	public static void createStateless() throws NamingException, InterruptedException {
		
		int i;
		long time = System.nanoTime();

		for (i = 1; i <= Variables.repeat; i++) {
			Util.invokeStatelessBean(i);
			Thread.sleep(Variables.sleep);
		}

		time = System.nanoTime() - time;
		System.out.println(Thread.currentThread().getId() + ": Number of stateless invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
	}
	
	/**
	 * Calls method Util.invokeStatefulBean in loop for as many times as is specified
	 * in Variables.repeat and measures the elapsed time.
	 * 
	 * @throws NamingException
	 * @throws InterruptedException 
	 */
	public static void createStateful() throws NamingException, InterruptedException {
		
		int i;
		long time = System.nanoTime();

		for (i = 1; i <= Variables.repeat; i++) {
			Util.invokeStatefulBean(i);
			Thread.sleep(Variables.sleep);
		}

		time = System.nanoTime()  - time;
		System.out.println(Thread.currentThread().getId() + ": Number of stateful invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
		
	}
	
	/**
	 * Looks up a remote stateless bean, then invokes method getMessage()
	 * on it for as many times as is defined in Variables.repeat() and measures
	 * elapsed time.
	 * 
	 * @throws NamingException
	 * @throws InterruptedException 
	 */
	public static void countWithStateless() throws NamingException, InterruptedException {
		
		int i;
		long time = System.nanoTime();
		
		final RemoteSLSB slb = lookupRemoteSLSB();
		if (Variables.verbose) {
			System.out.println(Thread.currentThread().getId() + ": Obtained a remote stateless bean for invocation.");
		}
		
		for (i = 1; i <= Variables.repeat; i++) {
			slb.getMessage();
			System.out.println(i);
			Thread.sleep(Variables.sleep);
		}
		
		time = System.nanoTime()  - time;
		System.out.println(Thread.currentThread().getId() + ": Number of stateless invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
		
	}
	
	/**
	 * Looks up a remote stateful bean, then invokes method incrementAndGetCounter()
	 * on it for as many times as is defined in Variables.repeat() and measures
	 * elapsed time.
	 * 
	 * @throws NamingException
	 * @throws InterruptedException 
	 */
	public static void countWithStateful() throws NamingException, InterruptedException {
		
		int i;
		long time = System.nanoTime();
		
		final RemoteSFSB sfb = lookupRemoteSFSB();
		if (Variables.verbose) {
			System.out.println(Thread.currentThread().getId() + ": Obtained a remote stateful bean for invocation.");
		}
        
		sfb.setCounter(0l);
		for (i = 1; i <= Variables.repeat; i++) {
			System.out.println(sfb.incrementAndGetCounter());
			Thread.sleep(Variables.sleep);
		}
		
		time = System.nanoTime()  - time;
		System.out.println(Thread.currentThread().getId() + ": Number of stateful invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
		
	}
	
	public static class StatelessCreationRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.createStateless();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static class StatelessRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.countWithStateless();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static class StatefulCreationRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.createStateful();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static class StatefulRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.countWithStateful();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
	
	public static class ContestRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.countWithStateful();
				Util.countWithStateless();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
	}
	
	public static class CreationContestRunner implements Runnable {

		@Override
		public void run() {
			try {
				Util.createStateful();
				Util.createStateless();
			} catch (NamingException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			} catch (InterruptedException ex) {
				Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
	}
	
}

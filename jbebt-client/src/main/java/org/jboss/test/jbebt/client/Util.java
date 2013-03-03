/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.client;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.test.jbebt.ejb.RemoteSFSB;
import org.jboss.test.jbebt.ejb.RemoteSLSB;

/**
 *
 * @author rjanik
 */
public class Util {
	
	public static RemoteSLSB lookupRemoteSLSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSLSBImpl!" + RemoteSLSB.class.getName();
		
		return (RemoteSLSB) context.lookup(ejbID);
	}
	
	public static RemoteSFSB lookupRemoteSFSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSFSBImpl!" + RemoteSFSB.class.getName() + "?stateful";
		
		return (RemoteSFSB) context.lookup(ejbID);
		
	}
	
	public static void invokeStatelessBean(int runNumber) throws NamingException {
		
		final RemoteSLSB slb = lookupRemoteSLSB();
        System.out.println("Obtained a remote stateless bean for invocation number " + runNumber);

		String node = slb.getNodeName();
		System.out.println("node: " + node);
        
	}
	
	public static void invokeStatefulBean(int runNumber) throws NamingException, InterruptedException {
		
		final RemoteSFSB sfb = lookupRemoteSFSB();
        System.out.println("Obtained a remote stateful bean for invocation number " + runNumber);

		String node = sfb.getNodeName();
		System.out.println("node: " + node);
        
	}
	
	public static void invokeStatelessRepeatedly() throws NamingException, InterruptedException {
		
		int i = 1;
		long time = System.nanoTime();
		
		if (Variables.repeat < 0) {
			while (true) {
				Util.invokeStatelessBean(i);
				i++;
				Thread.sleep(Variables.sleepTime);
			}
		} else {
			for (i = 1; i <= Variables.repeat; i++) {
				Util.invokeStatelessBean(i);
				Thread.sleep(Variables.sleepTime);
			}
		}
		
		time = System.nanoTime()  - time;
		System.out.println("Number of stateless invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
	}
	
	public static void invokeStatefulRepeatedly() throws NamingException, InterruptedException {
		
		int i = 1;
		long time = System.nanoTime();
		
		if (Variables.repeat < 0) {
			while (true) {
				Util.invokeStatefulBean(i);
				i++;
				Thread.sleep(Variables.sleepTime);
			}
		} else {
			for (i = 1; i <= Variables.repeat; i++) {
				Util.invokeStatefulBean(i);
				Thread.sleep(Variables.sleepTime);
			}
		}
		
		time = System.nanoTime()  - time;
		System.out.println("Number of stateful invocations: " + (i - 1) + ", elapsed time: " + ((double) time / 1000000000.0) + " seconds.");
		
	}
	
	public static void testStatefulFailover() throws NamingException, InterruptedException {
		
		final RemoteSFSB sfb = lookupRemoteSFSB();
        System.out.println("Obtained a remote stateful bean for invocation - testing failover.");
        
		sfb.setCounter(0l);
		for (int i = 0; i < Variables.repeat; i++) {
			System.out.println(sfb.incrementAndGetCounter());
			Thread.sleep(Variables.sleepTime);
		}
		
	}
	
}

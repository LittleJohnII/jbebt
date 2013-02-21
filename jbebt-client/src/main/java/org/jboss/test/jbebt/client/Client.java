/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.client;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.jboss.test.jbebt.ejb.RemoteSFSB;
import org.jboss.test.jbebt.ejb.RemoteSFSBImpl;
import org.jboss.test.jbebt.ejb.RemoteSLSB;
import org.jboss.test.jbebt.ejb.RemoteSLSBImpl;

/**
 * Standalone client for remote invocations.
 *
 * @author rjanik
 */
public class Client {

	public static void main(String[] args) throws Exception {

		invokeStatelessBean();
		
		invokeStatefulBean();

	}
	
	private static void invokeStatelessBean() throws NamingException {
		
		final RemoteSLSB slb = lookupRemoteSLSB();
        System.out.println("obtained a remote stateless bean for invocation");

		String message = slb.getMessage();
		System.out.println("message: " + message);

		String node = slb.getNodeName();
		System.out.println("node: " + node);
        
	}
	
	private static void invokeStatefulBean() throws NamingException {
		
        final RemoteSFSB sfb = lookupRemoteSFSB();
        System.out.println("obtained a remote stateful bean for invocation");
        
        byte[] data = sfb.getData();
		System.out.println(data[0] + data[1] + data[2]);
		data[0] = 1; data[1] = 2; data[2] = 3;
		int copied = sfb.setData(data);
		data = sfb.getData();
		System.out.println(data[0] + data[1] + data[2] + ", copied: " + copied);
		
		System.out.println(sfb.getCounter());
		System.out.println(sfb.incrementAndGetCounter());
		sfb.setCounter(200l);
		System.out.println(sfb.getCounter());
		
	}
	
	private static RemoteSLSB lookupRemoteSLSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		// final String appName = "";
		// final String moduleName = Variables.moduleName;
		// final String distinctName = "";
		// final String beanName = RemoteSLSBImpl.class.getSimpleName();
		// final String viewClassName = RemoteSLSB.class.getName();
		
		// System.out.println("ejb:" + appName + "/" + moduleName
		// 		+ "/" + distinctName + "/" + beanName + "!" + viewClassName);
		
		// return (RemoteSLSB) context.lookup("ejb:" + appName + "/" + moduleName
		// 		+ distinctName + "/" + beanName + "!" + viewClassName);
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSLSBImpl!" + RemoteSLSB.class.getName();
		
		return (RemoteSLSB) context.lookup(ejbID);
		
	}
	
	private static RemoteSFSB lookupRemoteSFSB() throws NamingException {
		
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
        
		// final String appName = "";
        // final String moduleName = Variables.moduleName;
        // final String distinctName = "";
        // final String beanName = RemoteSFSBImpl.class.getSimpleName();
		// final String viewClassName = RemoteSFSB.class.getName();
		
		// System.out.println("ejb:" + appName + "/" + moduleName
		// 		+ "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
        
		// return (RemoteSFSB) context.lookup("ejb:" + appName + "/" + moduleName
		// 		+ "/" + distinctName + "/" + beanName + "!" + viewClassName + "?stateful");
		
		String ejbID = "ejb:/jbebt-ejb/RemoteSFSBImpl!" + RemoteSFSB.class.getName() + "?stateful";
		
		return (RemoteSFSB) context.lookup(ejbID);
		
	}
	
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.client;

import javax.naming.Context;
import javax.naming.InitialContext;
import org.jboss.test.jbebt.common.StatelessSB;

/**
 *
 * @author rjanik
 */
public class Client {

	public static void main(String[] args) throws Exception {

		final String jndiName = "StatelessSB/remote";
		Context ic = new InitialContext();
		Object obj = ic.lookup(jndiName);
		System.out.println("client: lookup returned: " + obj);
		
		StatelessSB bean = (StatelessSB) obj;
		System.out.println(bean.getMessage());
		System.out.println(bean.getNodeName());

	}
}

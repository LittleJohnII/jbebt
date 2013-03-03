/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.ejb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;

/**
 * Remote stateless session bean. Encapsulates stateless session bean from jbebt-common.
 *
 * @author rjanik
 */
@Stateless
@Clustered
public class RemoteSLSBImpl implements RemoteSLSB {
	
	private final static Logger LOG = Logger.getLogger(RemoteSLSBImpl.class.getName());

	@Override
	public String getMessage() {
		LOG.log(Level.INFO, "invoking getMessage() on {0}", RemoteSLSBImpl.class.getName());
		return "Hello World!";
	}

	@Override
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on {0}", RemoteSLSBImpl.class.getName());
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");
			return null != jbossNodeName ? jbossNodeName : InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}

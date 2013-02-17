/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;

/**
 * Implementation of a simple stateless session bean interface.
 *
 * @author rjanik
 */
@Stateless
@Clustered
public class StatelessSBImpl implements StatelessSB {

	private final static Logger LOG = Logger.getLogger(StatelessSBImpl.class.getName());
	
	public StatelessSBImpl() {
		LOG.log(Level.INFO, "created {0} bean.", StatelessSBImpl.class.getName());
	}

	@Override
	public String getMessage() {
		LOG.log(Level.INFO, "invoking getMessage() on ", StatelessSBImpl.class.getName());
		return "Hello World!";
	}

	@Override
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on ", StatelessSBImpl.class.getName());
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");
			return null != jbossNodeName ? jbossNodeName : InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}
}

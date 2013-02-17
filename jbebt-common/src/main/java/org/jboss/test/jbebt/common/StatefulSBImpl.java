/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.common;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import org.jboss.ejb3.annotation.Clustered;

/**
 * Implementation of a simple stateful bean
 *
 * @author rjanik
 */
@Stateful
@Clustered
public class StatefulSBImpl implements StatefulSB {
	
	private final static Logger LOG = Logger.getLogger(StatelessSBImpl.class.getName());
	private final static int DATA_SIZE = 1024;
	private Byte[] data;
	
	public void StatefulSBImpl() {
		LOG.log(Level.INFO, "created {0} bean.", StatefulSBImpl.class.getName());
		data = new Byte[DATA_SIZE];
	}

	@Override
	public Byte[] getData() {
		LOG.log(Level.INFO, "invoking getData() on ", StatefulSBImpl.class.getName());
		return data;
	}

	@Override
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on ", StatefulSBImpl.class.getName());
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");
			return null != jbossNodeName ? jbossNodeName : InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}
	
}

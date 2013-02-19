/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.ejb;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import org.jboss.ejb3.annotation.Clustered;

/**
 * Implementation of a simple stateful bean.
 *
 * @author rjanik
 */
@Stateful
@Clustered
public class RemoteSFSBImpl implements RemoteSFSB {
	
	private final static Logger LOG = Logger.getLogger(RemoteSFSBImpl.class.getName());
	private final static int DATA_SIZE = 1024;
	private Byte[] data;
	private long counter;

	public RemoteSFSBImpl() {
		LOG.log(Level.INFO, "created {0} bean.", RemoteSFSBImpl.class.getName());
		data = new Byte[DATA_SIZE];
	}
	
	@Override
	public Byte[] getData() {
		LOG.log(Level.INFO, "invoking getData() on {0}", RemoteSFSBImpl.class.getName());
		return data;
	}

	@Override
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on {0}", RemoteSFSBImpl.class.getName());
		try {
			String jbossNodeName = System.getProperty("jboss.node.name");
			return null != jbossNodeName ? jbossNodeName : InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	@Override
	public long getCounter() {
		return this.counter;
	}
	
	@Override
	public void setCounter(long counter) {
		this.counter = counter;
	}
	
	@Override
	public long incrementAndGetCounter() {
		this.counter++;
		return this.counter;
	}
	
	
}

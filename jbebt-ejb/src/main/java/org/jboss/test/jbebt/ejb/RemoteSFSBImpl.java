/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import org.jboss.ejb3.annotation.Clustered;
import org.jboss.test.jbebt.common.StatefulSB;
import org.jboss.test.jbebt.common.StatefulSBImpl;

/**
 * Remote stateful session bean. Encapsulates stateful session bean from jbebt-common.
 *
 * @author rjanik
 */
@Stateful
@Clustered
public class RemoteSFSBImpl implements RemoteSB {
	
	private StatefulSB bean;
	private final static Logger LOG = Logger.getLogger(StatefulSBImpl.class.getName());

	public RemoteSFSBImpl() {
		LOG.log(Level.INFO, "created {0} bean.", RemoteSFSBImpl.class.getName());
		bean = new StatefulSBImpl();
	}
	
	public Byte[] getData() {
		LOG.log(Level.INFO, "invoking getData() on {0}", RemoteSFSBImpl.class.getName());
		return bean.getData();
	}
	
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on {0}", RemoteSFSBImpl.class.getName());
		return bean.getNodeName();
	}
	
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.ejb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import org.jboss.ejb3.annotation.Clustered;
import org.jboss.test.jbebt.common.StatelessSB;
import org.jboss.test.jbebt.common.StatelessSBImpl;

/**
 * Remote stateless session bean. Encapsulates stateless session bean from jbebt-common.
 *
 * @author rjanik
 */
@Stateless
@Clustered
public class RemoteSLSBImpl implements RemoteSB {
	
	private StatelessSB bean;
	private final static Logger LOG = Logger.getLogger(StatelessSBImpl.class.getName());

	public RemoteSLSBImpl() {
		LOG.log(Level.INFO, "created {0} bean.", RemoteSLSBImpl.class.getName());
		bean = new StatelessSBImpl();
	}
	
	public String getMessage() {
		LOG.log(Level.INFO, "invoking getMessage() on {0}", RemoteSLSBImpl.class.getName());
		return bean.getMessage();
	}
	
	public String getNodeName() {
		LOG.log(Level.INFO, "invoking getNodeName() on {0}", RemoteSLSBImpl.class.getName());
		return bean.getNodeName();
	}
	
}

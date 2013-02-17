/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.common;

import javax.ejb.Remote;

/**
 * A simple interface for stateful session beans.
 *
 * @author rjanik
 */
@Remote
public interface StatefulSB {
	
	public Byte[] getData();
	
	public String getNodeName();
	
}

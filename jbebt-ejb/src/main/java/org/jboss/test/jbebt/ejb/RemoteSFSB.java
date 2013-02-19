/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.ejb;

import javax.ejb.Remote;

/**
 * A simple interface for remote stateful session beans.
 *
 * @author rjanik
 */
@Remote
public interface RemoteSFSB {
	
	/**
	 * Get data method.
	 * 
	 * @return data, which is Byte[DATA_SIZE] (default for DATA_SIZE is 1024)
	 */
	public Byte[] getData();
	
	/**
	 * Get node name method. Returns jboss.node.name property or hostname if jboss.node.name is not defined.
	 * 
	 * @return node name, which is String
	 */
	public String getNodeName();
	
	/**
	 * Getter for counter attribute.
	 * 
	 * @return counter, which is long
	 */
	public long getCounter();
	
	/**
	 * Setter for counter attribute.
	 * 
	 * @param counter, which is long
	 */
	public void setCounter(long counter);
	
	/**
	 * Increment and get or counter attribute.
	 * 
	 * @return counter, which is long
	 */
	public long incrementAndGetCounter();
	
}

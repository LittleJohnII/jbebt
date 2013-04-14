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
	 * @return data, which is byte[DATA_SIZE] (default for DATA_SIZE is 1024)
	 */
	public byte[] getData();
	
	/**
	 * Set data method. This takes data parameter and copies all of its cells
	 * into given bean, or if there is less cells in beans data attribute
	 * (default 1024) it copies that many cells into the beans data attribute.
	 * Note that other cells will not be modified.
	 * 
	 * @param data, which is byte[]
	 * @return int, the number of cells copied
	 */
	public int setData(byte[] data);
	
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

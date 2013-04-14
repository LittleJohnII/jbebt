package org.jboss.test.jbebt.ejb;

import javax.ejb.Remote;

/**
 * A simple interface for remote stateless session beans.
 *
 * @author rjanik
 */
@Remote
public interface RemoteSLSB {
	
	/**
	 * A simple method that returns a simple message. Node independent.
	 *
	 * @return "Hello world!" message
	 */
	public String getMessage();

	/**
	 * A simple method that returns a node name. Node dependent.
	 *
	 * @return Name of the node the ejb is currently deployed on.
	 */
	public String getNodeName();
	
}

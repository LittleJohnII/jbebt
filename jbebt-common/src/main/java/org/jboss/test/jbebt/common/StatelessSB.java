/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jboss.test.jbebt.common;

import javax.ejb.Remote;

/**
 * A simple interface for stateless session beans.
 *
 * @author rjanik
 */
@Remote
public interface StatelessSB {

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

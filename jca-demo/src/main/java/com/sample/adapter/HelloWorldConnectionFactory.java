package com.sample.adapter;

import java.io.Serializable;

import javax.resource.Referenceable;
import javax.resource.ResourceException;

/**
 * HelloWorldConnectionFactory
 *
 * @version $Revision: $
 */
public interface HelloWorldConnectionFactory extends Serializable, Referenceable
{
   /** 
    * Get connection from factory
    *
    * @return HelloWorldConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   public HelloWorldConnection getConnection() throws ResourceException;

}

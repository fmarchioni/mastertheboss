package com.sample.adapter;

import javax.naming.NamingException;
import javax.naming.Reference;

import javax.resource.ResourceException;
import javax.resource.spi.ConnectionManager;

/**
 * HelloWorldConnectionFactoryImpl
 *
 * @version $Revision: $
 */
public class HelloWorldConnectionFactoryImpl implements HelloWorldConnectionFactory
{
   /** The serialVersionUID */
   private static final long serialVersionUID = 1L;

   private Reference reference;

   private HelloWorldManagedConnectionFactory mcf;
   private ConnectionManager connectionManager;

   /**
    * Default constructor
    * @param mcf ManagedConnectionFactory
    * @param cxManager ConnectionManager
    */
   public HelloWorldConnectionFactoryImpl(HelloWorldManagedConnectionFactory mcf,
                                          ConnectionManager cxManager)
   {
      this.mcf = mcf;
      this.connectionManager = cxManager;
   }

   /** 
    * Get connection from factory
    *
    * @return HelloWorldConnection instance
    * @exception ResourceException Thrown if a connection can't be obtained
    */
   @Override
   public HelloWorldConnection getConnection() throws ResourceException
   {
      return (HelloWorldConnection)connectionManager.allocateConnection(mcf, null);
   }

   /**
    * Get the Reference instance.
    *
    * @return Reference instance
    * @exception NamingException Thrown if a reference can't be obtained
    */
   @Override
   public Reference getReference() throws NamingException
   {
      return reference;
   }

   /**
    * Set the Reference instance.
    *
    * @param reference A Reference instance
    */
   @Override
   public void setReference(Reference reference)
   {
      this.reference = reference;
   }
}
package com.sample.adapter;

import java.util.logging.Logger;

/**
 * HelloWorldConnectionImpl
 *
 * @version $Revision: $
 */
public class HelloWorldConnectionImpl implements HelloWorldConnection
{
   /** The logger */
   private static Logger log = Logger.getLogger("HelloWorldConnectionImpl");

   /** ManagedConnection */
   private HelloWorldManagedConnection mc;

   /** ManagedConnectionFactory */
   private HelloWorldManagedConnectionFactory mcf;

   /**
    * Default constructor
    * @param mc HelloWorldManagedConnection
    * @param mcf HelloWorldManagedConnectionFactory
    */
   public HelloWorldConnectionImpl(HelloWorldManagedConnection mc,
                                   HelloWorldManagedConnectionFactory mcf)
   {
      this.mc = mc;
      this.mcf = mcf;
   }

   /**
    * Call helloWorld
    * @return String helloworld
    */
   public String helloWorld()
   {
      return helloWorld(((HelloWorldResourceAdapter)mcf.getResourceAdapter()).getName());
   }

   /**
    * Call helloWorld
    * @param name String name
    * @return String helloworld
    */
   public String helloWorld(String name)
   {
      return mc.helloWorld(name);
   }

   /**
    * Close
    */
   public void close()
   {
      mc.closeHandle(this);
   }
}

package com.sample;


import org.jboss.ejb3.annotation.Cache;
import jakarta.ejb.Stateful;

@Stateful
@Cache("custom-distributable")
public class SampleSFSB {
 

    int i=1;
    public int counter() {

        i++;
        return i;
	 
    }

}

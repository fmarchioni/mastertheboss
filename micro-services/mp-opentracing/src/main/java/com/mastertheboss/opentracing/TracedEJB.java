package com.mastertheboss.opentracing;

import javax.ejb.Stateless;

import org.eclipse.microprofile.opentracing.Traced;

@Stateless
@Traced
public class TracedEJB {
    public void onNewOrder() {
        System.out.println("Action through EJB!");
    }
}

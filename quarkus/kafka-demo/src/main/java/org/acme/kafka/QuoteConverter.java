package org.acme.kafka;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.reactive.messaging.annotations.Broadcast;

@ApplicationScoped
public class QuoteConverter {
 
	DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");  

    @Incoming("stocks")
    @Outgoing("in-memory-stream")
    @Broadcast
    public Quote newQuote(Quote quote) throws Exception {
 
        Date date = Calendar.getInstance().getTime();        
        String strDate = dateFormat.format(date);  
        quote.setTime(strDate);
 
    	return quote;
     
    }

}

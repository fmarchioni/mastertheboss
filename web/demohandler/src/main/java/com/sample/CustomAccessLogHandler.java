package com.sample;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
 
 

public class CustomAccessLogHandler extends Handler {

 
	private final Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public void publish(LogRecord record) {
		try {
			log.fine("------------------ Sending: "+ record.getMessage());
	 
		} catch (Exception e) {
			log.warning("Error in heclogger");
		}
	}

	@Override
	public void flush() {
		log.fine("Flushing heclogger");
	}

	@Override
	public void close() {
		log.fine("Closing heclogger");
	}

	 
	private String readEnvProperty(String key) {
		return Optional.ofNullable(System.getenv(key)).orElse("");
	}
	
	
}
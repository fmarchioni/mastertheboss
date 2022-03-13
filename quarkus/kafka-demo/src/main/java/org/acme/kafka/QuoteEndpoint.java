package org.acme.kafka;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.reactivestreams.Publisher;

@Path("/quotes")
public class QuoteEndpoint {

	@Channel("in-memory-stream")
	Publisher<Quote> quotes;

	@ConfigProperty(name = "stock.tickers")
	List<String> stocks;

	@GET
	@Path("/stream")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	@SseElementType(MediaType.APPLICATION_JSON)
	public Publisher<Quote> stream() {
		return quotes;
	}

	@GET
	@Path("/init")
	public List<Quote> getList() {
		List<Quote> list = new ArrayList();
		for (String ticker : stocks) {
			list.add(new Quote(ticker));
		}
		return list;
	}

}

package org.acme.kafka;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@ApplicationScoped
public class QuoteGenerator {

	int counter = 0;

	@ConfigProperty(name = "stock.tickers")
	List<String> stocks;

	@Outgoing("stock-quote")
	public Multi<Quote> generate() {
		return Multi.createFrom().ticks().every(Duration.ofSeconds(1)).map(n -> generateQuote());
	}

	private Quote generateQuote() {
		Stock stock = null;
		String ticker = stocks.get(getCounter());
		try {
			stock = YahooFinance.get(ticker);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal price = stock.getQuote().getPrice();
		BigDecimal priceChange = stock.getQuote().getChange();

		Quote q = new Quote();
		q.setCompany(ticker);
		q.setValue(price.doubleValue());
		q.setChange(priceChange.doubleValue());

		return q;
	}

	private int getCounter() {
		counter++;
		if (counter == stocks.size()) {
			counter = 0;
		}
		return counter;
	}

}

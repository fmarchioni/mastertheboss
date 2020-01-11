package com.mastertheboss;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

import io.undertow.util.StatusCodes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimedHandler implements HttpHandler {

    private HttpHandler next;

    public TimedHandler(HttpHandler next) {
        this.next = next;
    }

    // Default values
    String from ="09:00";
    String to ="13:00";

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public void handleRequest(final HttpServerExchange exchange) throws Exception {


        if (checkTime()) {
            next.handleRequest(exchange);
        } else {
            exchange.setStatusCode(StatusCodes.SERVICE_UNAVAILABLE);
            exchange.endExchange();
        }
    }


    public boolean checkTime() {
        Calendar now = Calendar.getInstance();

        int hour = now.get(Calendar.HOUR_OF_DAY); // Get hour in 24 hour format
        int minute = now.get(Calendar.MINUTE);

        Date date = parseDate(hour + ":" + minute);
        Date dateCompareOne = parseDate(from);
        Date dateCompareTwo = parseDate(to);

        if (dateCompareOne.before( date ) && dateCompareTwo.after(date)) {
            System.out.println("Passed Compared "+from+ " and "+to + " with "+date);
            return true;
        }
        System.out.println("Passed Compared "+from+ " and "+to + " with "+date);
        return false;
    }
    private Date parseDate(String date) {

        final String inputFormat = "HH:mm";
        SimpleDateFormat inputParser = new SimpleDateFormat(inputFormat, Locale.US);
        try {
            return inputParser.parse(date);
        } catch (java.text.ParseException e) {
            return new Date(0);
        }
    }

}
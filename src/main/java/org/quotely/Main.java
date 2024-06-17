package org.quotely;

public class Main {
    public static void main(String[] args) {
        ConsoleWriter writer = new ConsoleWriter(System.out);
        QuoteService quoteService = new QuoteService(writer);
        quoteService.generateQuote(args);
    }
}
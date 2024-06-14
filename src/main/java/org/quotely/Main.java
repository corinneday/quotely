package org.quotely;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) {
        // TODO: validation of args (usage/help message if invalid?)
        String language = "en";
        if (args.length != 0) {
            if ("russian".equalsIgnoreCase(args[0])) {
                language = "ru";
            }
        }

        // TODO: move this out of main
        URI uri = URI.create("http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=" + language);
        ObjectMapper objectMapper = new ObjectMapper();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Quote quote = objectMapper.readValue(response.body(), Quote.class);
            System.out.println(quote.getQuoteText());
            System.out.println(quote.getQuoteAuthor());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
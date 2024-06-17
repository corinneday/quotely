package org.quotely;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class QuoteService {
    private final ConsoleWriter writer;
    private static final String QUOTE_URI = "http://api.forismatic.com/api/1.0/?method=getQuote&format=json&lang=";

    public QuoteService(ConsoleWriter writer) {
        this.writer = writer;
    }

    public void generateQuote(String[] args) {
        Language language = getLanguage(args);
        Quote quote = getQuote(language);
        writer.writeQuote(quote);
    }

    public Language getLanguage(String[] args) {
        ConsoleWriter writer = new ConsoleWriter(System.out);
        Language language = Language.ENGLISH;
        if (args.length != 0) {
            if ("russian".equalsIgnoreCase(args[0])) {
                language = Language.RUSSIAN;
            } else if (args.length > 1 || !"english".equalsIgnoreCase(args[0])) {
                writer.writeUsageInfo(Arrays.toString(args));
                throw new RuntimeException("Invalid arguments provided.");
            }
        }
        return language;
    }

    public Quote getQuote(Language language) {
        URI uri = URI.create(QUOTE_URI + language.getShortName());
        ObjectMapper objectMapper = new ObjectMapper();
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // TODO: don't have time to look into it more, but it looks like single quotes are double escaped and the jackson parsing was failing
            String body = response.body().replace("\\'", "\'");
            return objectMapper.readValue(body, Quote.class);
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
        }

        return null;
    }
}

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quotely.ConsoleWriter;
import org.quotely.Quote;
import org.quotely.QuoteService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    @Spy private final ConsoleWriter consoleWriter = new ConsoleWriter(new PrintStream(outStream));
    // TODO: mock HttpClient in tests
    //when(httpClient.send(any(), any(HttpResponse.BodyHandlers.ofString().getClass()))).thenReturn(mockResponse);
    //when(response.body()).thenReturn(sample);

    @Test
    public void getQuoteEnglish() throws IOException, InterruptedException {
        QuoteService quoteService = new QuoteService(consoleWriter);
        String[] args = new String[] {"English"};
        quoteService.generateQuote(args);
        assertFalse(outStream.toString().isEmpty());
        verify(consoleWriter, never()).writeUsageInfo(any());
    }

    @Test
    public void getQuoteRussian() throws IOException, InterruptedException {
        QuoteService quoteService = new QuoteService(consoleWriter);
        String[] args = new String[] {"Russian"};
        quoteService.generateQuote(args);
        assertFalse(outStream.toString().isEmpty());
        verify(consoleWriter, never()).writeUsageInfo(any());
    }

    @Test
    public void getQuoteInvalidArgs() throws IOException, InterruptedException {
        QuoteService quoteService = new QuoteService(consoleWriter);
        String[] args = new String[] {"French"};
        RuntimeException exception = assertThrows(RuntimeException.class, () -> quoteService.generateQuote(args));
        assertEquals("Invalid arguments provided.", exception.getMessage());
    }

    // TODO: use this with mock HttpClient
    private Quote sampleQuote() throws JsonProcessingException {
        String sample = "{\"quoteText\":\"Leaders aren\\'t born they are made. And they are made just like anything else, through hard work. And that\\'s the price well have to pay to achieve that goal, or any goal.\", \"quoteAuthor\":\"Vince Lombardi\", \"senderName\":\"\", \"senderLink\":\"\", \"quoteLink\":\"http://forismatic.com/en/c169d6598f/\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        String body = sample.replace("\\'", "\'");
        return objectMapper.readValue(body, Quote.class);
    }

}

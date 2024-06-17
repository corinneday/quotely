package org.quotely;

import java.io.PrintStream;

public class ConsoleWriter {

    PrintStream printStream;

    public ConsoleWriter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void writeQuote(Quote quote) {
        printStream.println(quote.getText());
        printStream.println(quote.getAuthor());
    }

    public void writeUsageInfo(String arguments) {
        System.out.println("Invalid arguments provided: " + arguments);
        System.out.println("Usage: java -jar quotely.jar [language]");
        System.out.println("    Accepted languages are English and Russian.");
    }
}

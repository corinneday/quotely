package org.quotely;

import java.util.Objects;

public class Quote {
    private String quoteText;
    private String quoteAuthor;
    private String senderName;
    private String senderLink;
    private String quoteLink;

    public Quote() {
        // default constructor
    }

    public Quote(String quoteLink, String senderLink, String senderName, String quoteAuthor, String quoteText) {
        this.quoteLink = quoteLink;
        this.senderLink = senderLink;
        this.senderName = senderName;
        this.quoteAuthor = quoteAuthor;
        this.quoteText = quoteText;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderLink() {
        return senderLink;
    }

    public void setSenderLink(String senderLink) {
        this.senderLink = senderLink;
    }

    public String getQuoteLink() {
        return quoteLink;
    }

    public void setQuoteLink(String quoteLink) {
        this.quoteLink = quoteLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quote quote = (Quote) o;
        return Objects.equals(quoteText, quote.quoteText) && Objects.equals(quoteAuthor, quote.quoteAuthor) && Objects.equals(senderName, quote.senderName) && Objects.equals(senderLink, quote.senderLink) && Objects.equals(quoteLink, quote.quoteLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quoteText, quoteAuthor, senderName, senderLink, quoteLink);
    }

    @Override
    public String toString() {
        return "Quote{" +
                "quoteText='" + quoteText + '\'' +
                ", quoteAuthor='" + quoteAuthor + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderLink='" + senderLink + '\'' +
                ", quoteLink='" + quoteLink + '\'' +
                '}';
    }
}

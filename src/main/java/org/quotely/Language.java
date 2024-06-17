package org.quotely;

public enum Language {
    ENGLISH ("en"),
    RUSSIAN ("ru");

    private final String shortName;

    Language(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }
}

package com._7e3t.idGenerator.algorithms;

import java.util.Arrays;
import java.util.List;

public class IdGeneratorFormat {

    private String format;
    private List<String> contains;

    public IdGeneratorFormat(String format) {
        this.format = format;
    }

    public IdGeneratorFormat(String format, String... contains) {
        this.format = format;
        this.contains = Arrays.asList(contains);
    }

    public String getFormat() {
        return format;
    }

    public List<String> getContains() {
        return contains;
    }
}

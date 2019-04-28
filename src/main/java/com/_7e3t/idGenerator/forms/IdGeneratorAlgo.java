package com._7e3t.idGenerator.forms;

import java.util.Arrays;
import java.util.List;

public class IdGeneratorAlgo {

    private String prefix = "";
    private String suffix = "";
    private Integer len;
    private CharType charType = CharType.NONE;
    private List<String> contains;

    public IdGeneratorAlgo(Integer len, CharType charType) {
        this.len = len;
        this.charType = charType;
    }

    public IdGeneratorAlgo(String prefix, String suffix, Integer len, CharType charType) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.len = len;
        this.charType = charType;
    }

    public IdGeneratorAlgo(Integer len, String... myContains) {
        this.len = len;
        this.contains = Arrays.asList(myContains);
    }

    public IdGeneratorAlgo(String prefix, String suffix, Integer len, String... myContains) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.len = len;
        this.contains = Arrays.asList(myContains);
    }

    public IdGeneratorAlgo(String prefix, String suffix, Integer len, CharType charType, String... myContains) {
        this.prefix = prefix;
        this.suffix = suffix;
        this.len = Math.abs(len);
        this.charType = charType;

        String[] contains = new String[charType.getContains().length + myContains.length];
        System.arraycopy(myContains, 0, contains, 0, myContains.length);
        System.arraycopy(charType.getContains(), 0, contains, myContains.length, charType.getContains().length);

        this.contains = Arrays.asList(contains);
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public Integer getLen() {
        return len;
    }

    public CharType getCharType() {
        return charType;
    }

    public List<String> getContains() {
        return contains;
    }
}

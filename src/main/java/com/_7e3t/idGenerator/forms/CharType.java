package com._7e3t.idGenerator.forms;

public enum CharType {
    NUMBER(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
    ), LETTER(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "g",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "G",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ), LOWERCASE_LETTER(
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "g",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z"
    ), UPPERCASE_LETTER(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "G",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ), ALL(
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "g",
            "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
            "u", "v", "w", "x", "y", "z",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "G",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    ), NONE();

    String [] contains;

    CharType(String ... contains) {
        this.contains = contains;
    }

    public String[] getContains() {
        return contains;
    }
}

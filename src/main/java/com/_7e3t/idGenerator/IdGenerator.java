package com._7e3t.idGenerator;

import com._7e3t.idGenerator.algorithms.CharType;
import com._7e3t.idGenerator.algorithms.IdGeneratorFormat;
import com._7e3t.idGenerator.datas.DataSourceInfo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class IdGenerator {

    private IdGeneratorFormat format;
    private Set<String> ids = new HashSet<>();

    public IdGenerator(IdGeneratorFormat format) {
        this.format = format;
    }

    public IdGenerator(IdGeneratorFormat format, String... previousIds) {
        this.format = format;
        for (int i = 0; i < previousIds.length; i++) {
            ids.add(previousIds[i]);
        }
    }

    public IdGenerator(IdGeneratorFormat idGeneratorAlgorithm, DataSourceInfo dataSourceInfo) {
        this(idGeneratorAlgorithm);

        try (
                Connection connection = dataSourceInfo.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT " + dataSourceInfo.getCol() + " FROM " + dataSourceInfo.getTable())
        ) {
            while (resultSet.next()) {
                ids.add(resultSet.getString(dataSourceInfo.getCol()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generate() {
        StringBuilder newId;
        do {
            newId = new StringBuilder();
            for (int i = 0; i < format.getFormat().length(); i++) {
                if (format.getFormat().charAt(i) == '?') {
                    newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.ALL.getContains()), format.getContains())));
                    continue;
                }
                if (format.getFormat().charAt(i) == 'd') {
                    newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.NUMBER.getContains()))));
                    continue;
                }
                if (format.getFormat().charAt(i) == 'l') {
                    newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LOWERCASE_LETTER.getContains()))));
                    continue;
                }
                if (format.getFormat().charAt(i) == 'L') {
                    newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.UPPERCASE_LETTER.getContains()))));
                    continue;
                }
                if (format.getFormat().charAt(i) == 'c') {
                    newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LETTER.getContains()))));
                    continue;
                }
                if (format.getFormat().charAt(i) == 's') {
                    newId.append(getRandomChar(concatCollections(format.getContains())));
                    continue;
                }
                if (format.getFormat().charAt(i) == '[') {
                    StringBuilder formatType = new StringBuilder();
                    while (format.getFormat().charAt(++i) != ']') {
                        formatType.append(format.getFormat().charAt(i));
                    }
                    switch (formatType.toString()) {
                        case "dl":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LOWERCASE_LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains()))));
                            continue;
                        case "dL":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.UPPERCASE_LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains()))));
                            continue;
                        case "dc":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains()))));
                            continue;
                        case "ds":
                            newId.append(getRandomChar(concatCollections(format.getContains()
                                    , Arrays.asList(CharType.NUMBER.getContains()))));
                            continue;
                        case "dsl":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LOWERCASE_LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains())
                                    , format.getContains())));
                            continue;
                        case "dsL":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.UPPERCASE_LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains())
                                    , format.getContains())));
                            continue;
                        case "dsc":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LETTER.getContains())
                                    , Arrays.asList(CharType.NUMBER.getContains())
                                    , format.getContains())));
                            continue;
                        case "sl":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LOWERCASE_LETTER.getContains())
                                    , format.getContains())));
                            continue;
                        case "sL":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.UPPERCASE_LETTER.getContains())
                                    , format.getContains())));
                            continue;
                        case "sc":
                            newId.append(getRandomChar(concatCollections(Arrays.asList(CharType.LETTER.getContains())
                                    , format.getContains())));
                            continue;
                    }
                }
                if (format.getFormat().charAt(i) == '{') {
                    while (format.getFormat().charAt(++i) != '}') {
                        newId.append(format.getFormat().charAt(i));
                    }
                }
            }
        } while (!ids.add(newId.toString()));
        return newId.toString();
    }

    @SafeVarargs
    private List<String> concatCollections(List<String>... collections) {
        List<String> result = new ArrayList<>();
        for (List<String> collection : collections) {
            result.addAll(collection);
        }
        return result;
    }

    private String getRandomChar(List<String> candidate) {
        return candidate.get(Double.valueOf((Math.random() * Integer.MAX_VALUE)).intValue() % candidate.size());
    }

    public IdGeneratorFormat getFormat() {
        return format;
    }

    public void setFormat(IdGeneratorFormat format) {
        this.format = format;
    }

}

package com._7e3t.idGenerator;

import com._7e3t.idGenerator.forms.DataSourceInfo;
import com._7e3t.idGenerator.forms.IdGeneratorAlgo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class IdGenerator {

    private IdGeneratorAlgo algo;
    private Set<String> ids = new HashSet<>();

    public IdGenerator(IdGeneratorAlgo algo) {
        this.algo = algo;
    }

    public IdGenerator(IdGeneratorAlgo algo, String ... previousIds) {
        this.algo = algo;
        for (int i = 0; i < previousIds.length; i++) {
            ids.add(previousIds[i]);
        }
    }

    public IdGenerator(IdGeneratorAlgo idGeneratorAlgo, DataSourceInfo dataSourceInfo) {
        this(idGeneratorAlgo);

        try(
                Connection connection = dataSourceInfo.getDataSource().getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT "+ dataSourceInfo.getCol() + " FROM " + dataSourceInfo.getTable())
        ) {
            while (resultSet.next()) {
                ids.add(resultSet.getString(dataSourceInfo.getCol()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String generate() {
        int initLen = ids.size();
        StringBuilder newId = new StringBuilder();
        while(ids.size() == initLen) {
            newId.append(algo.getPrefix());
            if (algo.getContains().size() > 0) {
                for (int i = 0; i < algo.getLen(); i++) {
                    newId.append(algo.getContains().get(Double.valueOf((Math.random()*Integer.MAX_VALUE)).intValue()%algo.getContains().size()));
                }
            }
            newId.append(algo.getSuffix());
            ids.add(newId.toString());
        }
        return newId.toString();
    }

    public IdGeneratorAlgo getAlgo() {
        return algo;
    }

    public void setAlgo(IdGeneratorAlgo algo) {
        this.algo = algo;
    }

    public Set<String> getIds() {
        return ids;
    }

}

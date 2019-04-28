package com._7e3t.idGenerator.forms;

import javax.sql.DataSource;

public class DataSourceInfo {

    private DataSource dataSource;
    private String table;
    private String col;

    public DataSourceInfo(DataSource dataSource, String table, String col) {
        this.dataSource = dataSource;
        this.table = table;
        this.col = col;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getCol() {
        return col;
    }

    public void setCol(String col) {
        this.col = col;
    }
}

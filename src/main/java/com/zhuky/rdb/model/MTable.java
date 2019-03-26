package com.zhuky.rdb.model;

import java.util.Set;

public class MTable {

    private String tableName;
    private Set<String> indexNames;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Set<String> getIndexNames() {
        return indexNames;
    }

    public void setIndexNames(Set<String> indexNames) {
        this.indexNames = indexNames;
    }

    @Override
    public String toString() {
        return "MTable{" +
                "tableName='" + tableName + '\'' +
                ", indexNames=" + indexNames +
                '}';
    }
}

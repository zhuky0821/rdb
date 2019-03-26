package com.zhuky.rdb.model;

import java.util.Set;

public class MIndex {
    private String indexName;
    private Set<String> rowIds;

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public Set<String> getRowIds() {
        return rowIds;
    }

    public void setRowIds(Set<String> rowIds) {
        this.rowIds = rowIds;
    }

    @Override
    public String toString() {
        return "MIndex{" +
                "indexName='" + indexName + '\'' +
                ", rowIds=" + rowIds +
                '}';
    }



}

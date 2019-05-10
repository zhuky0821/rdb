package com.zhuky.rdb.describe;

import java.util.Map;

public class TableIndexAndCol {
    private Map<String, Map<String, String[]>> index;
    private Map<String, String[]> column;

    public Map<String, Map<String, String[]>> getIndex() {
        return index;
    }

    public Map<String, String[]> getColumn() {
        return column;
    }

    public TableIndexAndCol(Map<String, Map<String, String[]>> index, Map<String, String[]> column) {
        this.index = index;
        this.column = column;
    }
}

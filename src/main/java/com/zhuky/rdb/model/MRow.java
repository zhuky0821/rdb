package com.zhuky.rdb.model;

import java.util.Map;

public class MRow {
    private String rowId;
    private Map<String, Object> columnsData;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public Map<String, Object> getColumnsData() {
        return columnsData;
    }

    public void setColumnsData(Map<String, Object> columnsData) {
        this.columnsData = columnsData;
    }

    @Override
    public String toString() {
        return "MRow{" +
                "rowId='" + rowId + '\'' +
                ", columnsData=" + columnsData +
                '}';
    }
}

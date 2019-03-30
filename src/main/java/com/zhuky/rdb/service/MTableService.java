package com.zhuky.rdb.service;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.model.MRow;

public interface MTableService {
    public void insert(String tableName, MRow row) throws BusinessException;
}

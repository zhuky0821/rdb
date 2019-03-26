package com.zhuky.rdb.dao;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.model.MTable;

public interface MTableDao {

    public void insert(String tableName, MTable mTable) throws BusinessException;

    public void update(String tableName, MTable mTable) throws BusinessException;

//    public void delete(String tableName, )

}

package com.zhuky.rdb.dao.impl;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.dao.MRowDao;
import com.zhuky.rdb.model.MRow;
import com.zhuky.redis.RedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("mRowDao")
public class MRowDaoImpl implements MRowDao {

    Logger log = LogManager.getLogger(MRowDaoImpl.class);

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void insert(MRow mRow) throws BusinessException {

        redisUtil.hmset(mRow.getRowId(), mRow.getColumnsData());

    }

    @Override
    public void delete(MRow mRow) throws BusinessException {
        redisUtil.hdel(mRow.getRowId());
    }

    @Override
    public void deleteBatch(Set<MRow> mRows) throws BusinessException {
        Iterator iterator = mRows.iterator();
        while (iterator.hasNext()){
            redisUtil.hdel(((MRow)iterator.next()).getRowId());
        }
    }

    @Override
    public void update(MRow mRow) throws BusinessException {
        insert(mRow);
    }

    @Override
    public void update(Set<MRow> mRows) throws BusinessException {
        Iterator iterator = mRows.iterator();
        while (iterator.hasNext()){
            insert((MRow)iterator.next());
        }
    }

    @Override
    public MRow getByRowId(String rowId) throws BusinessException {

        Map<Object, Object> data = redisUtil.hmget(rowId);

        MRow mRow = new MRow();
        mRow.setRowId(rowId);
        Map<String, Object> columnsData = new HashMap<>();
        Iterator<Map.Entry<Object, Object>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<Object, Object> entry = iterator.next();
            columnsData.put((String)entry.getKey(), entry.getValue());
        }
        mRow.setColumnsData(columnsData);

        return mRow;
    }

    @Override
    public Set<MRow> getByRowIdBatch(Set<String> rowIds) throws BusinessException {
        Set<MRow> mRows = new HashSet<>();

        for (String rowId : rowIds) {
            MRow mRow = getByRowId(rowId);
            mRows.add(mRow);
        }
        return mRows;
    }
}

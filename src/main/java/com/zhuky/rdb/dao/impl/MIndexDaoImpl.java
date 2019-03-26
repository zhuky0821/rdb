package com.zhuky.rdb.dao.impl;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.dao.MIndexDao;
import com.zhuky.rdb.dao.MRowDao;
import com.zhuky.rdb.model.MIndex;
import com.zhuky.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.Set;

@Repository("mIndexDao")
public class MIndexDaoImpl implements MIndexDao {

    @Autowired
    private MRowDao mRowDao;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void insert(MIndex mIndex) throws BusinessException {

        Array[] rowIds = new Array[mIndex.getRowIds().size()];
        mIndex.getRowIds().toArray(rowIds);
        redisUtil.sSet(mIndex.getIndexName(), rowIds);
    }

    @Override
    public void insertBatch(Set<MIndex> mIndexSet) throws BusinessException {
        for (MIndex mIndex : mIndexSet
             ) {
            insert(mIndex);
        }
    }

    @Override
    public void delete(MIndex mIndex) throws BusinessException {
        Array[] values = new Array[mIndex.getRowIds().size()];
        mIndex.getRowIds().toArray(values);
        redisUtil.setRemove(mIndex.getIndexName(), values);
    }

    @Override
    public void deleteByKey(MIndex mIndex) throws BusinessException {
        redisUtil.setRemove(mIndex.getIndexName());
    }

    @Override
    public void deleteBatch(Set<MIndex> mIndexSet) throws BusinessException {
        for (MIndex mIndex:mIndexSet
             ) {
            delete(mIndex);
        }
    }
}

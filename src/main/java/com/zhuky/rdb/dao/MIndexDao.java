package com.zhuky.rdb.dao;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.model.MIndex;

import java.util.Set;

public interface MIndexDao {

    /**
     * 插入一条索引值
     * @param mIndex
     * @throws BusinessException
     */
    public void insert(MIndex mIndex) throws BusinessException;

    /**
     * 索引批量插入
     * @param mIndexSet
     * @throws BusinessException
     */
    public void insertBatch(Set<MIndex> mIndexSet) throws BusinessException;

    /**
     * 删除索引下的一个条目
     * @param mIndex
     * @throws BusinessException
     */
    public void delete(MIndex mIndex) throws BusinessException;

    /**
     * 删除该索引下的所有条目
     * @param mIndex
     * @throws BusinessException
     */
    public void deleteByKey(MIndex mIndex) throws BusinessException;

    /**
     * 批量删除索引下的条目
     * @param mIndexSet
     * @throws BusinessException
     */
    public void deleteBatch(Set<MIndex> mIndexSet) throws BusinessException;

}

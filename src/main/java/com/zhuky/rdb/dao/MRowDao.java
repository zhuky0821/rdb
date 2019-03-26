package com.zhuky.rdb.dao;

import com.zhuky.exception.BusinessException;
import com.zhuky.rdb.model.MRow;

import java.util.Set;

public interface MRowDao {

    /**
     * 插入一行数据
     * @param mRow
     * @throws BusinessException
     */
    public void insert(MRow mRow) throws BusinessException;

    /**
     * 删除一行数据
     * @param mRow
     * @throws BusinessException
     */
    public void delete(MRow mRow) throws BusinessException;

    /**
     * 批量删除数据
     * @param mRows
     * @throws BusinessException
     */
    public void deleteBatch(Set<MRow> mRows) throws BusinessException;

    /**
     * 更新数据
     * @param mRow
     * @throws BusinessException
     */
    public void update(MRow mRow) throws BusinessException;

    /**
     * 批量更新
     * @param mRows
     * @throws BusinessException
     */
    public void update(Set<MRow> mRows) throws BusinessException;

    /**
     * 通过rowId 查询数据
     * @param rowId
     * @return
     * @throws BusinessException
     */
    public MRow getByRowId(String rowId) throws BusinessException;

    /**
     * 批量通过rowId 查询数据
     * @param rowIds
     * @return
     * @throws BusinessException
     */
    public Set<MRow> getByRowIdBatch(Set<String> rowIds) throws BusinessException;

}

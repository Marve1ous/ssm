package cn.marve1ous.dao;

import cn.marve1ous.model.Result;

public interface ResultMapper {
    int deleteByPrimaryKey(Integer resultid);

    int insert(Result record);

    int insertSelective(Result record);

    Result selectByPrimaryKey(Integer resultid);

    int updateByPrimaryKeySelective(Result record);

    int updateByPrimaryKey(Result record);
}
package cn.marve1ous.dao;

import cn.marve1ous.model.Turn;

public interface TurnMapper {
    int deleteByPrimaryKey(String turnid);

    int insert(Turn record);

    int insertSelective(Turn record);

    Turn selectByPrimaryKey(String turnid);

    int updateByPrimaryKeySelective(Turn record);

    int updateByPrimaryKey(Turn record);
}
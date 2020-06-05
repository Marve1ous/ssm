package cn.marve1ous.dao;

import cn.marve1ous.model.Classlesson;

public interface ClasslessonMapper {
    int insert(Classlesson record);

    int insertSelective(Classlesson record);
}
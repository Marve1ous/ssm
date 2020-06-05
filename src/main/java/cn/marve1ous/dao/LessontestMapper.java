package cn.marve1ous.dao;

import cn.marve1ous.model.Lessontest;

public interface LessontestMapper {
    int insert(Lessontest record);

    int insertSelective(Lessontest record);
}
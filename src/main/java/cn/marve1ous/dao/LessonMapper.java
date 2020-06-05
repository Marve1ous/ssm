package cn.marve1ous.dao;

import cn.marve1ous.model.Lesson;

public interface LessonMapper {
    int deleteByPrimaryKey(String lessonid);

    int insert(Lesson record);

    int insertSelective(Lesson record);

    Lesson selectByPrimaryKey(String lessonid);

    int updateByPrimaryKeySelective(Lesson record);

    int updateByPrimaryKey(Lesson record);
}
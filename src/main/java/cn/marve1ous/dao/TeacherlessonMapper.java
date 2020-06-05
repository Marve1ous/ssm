package cn.marve1ous.dao;

import cn.marve1ous.model.Teacherlesson;

public interface TeacherlessonMapper {
    int insert(Teacherlesson record);

    int insertSelective(Teacherlesson record);
}
package cn.marve1ous.dao;

import cn.marve1ous.model.Stugrade;

public interface StugradeMapper {
    int insert(Stugrade record);

    int insertSelective(Stugrade record);
}
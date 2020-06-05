package cn.marve1ous.dao;

import cn.marve1ous.model.Userrole;

public interface UserroleMapper {
    int insert(Userrole record);

    int insertSelective(Userrole record);

    int deleteByPrimaryKey(String userid);
}
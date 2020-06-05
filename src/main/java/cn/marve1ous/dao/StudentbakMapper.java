package cn.marve1ous.dao;

import cn.marve1ous.model.Studentbak;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface StudentbakMapper {
    int deleteByPrimaryKey(String stuid);

    int insert(Studentbak record);

    int insertSelective(Studentbak record);

    Studentbak selectByPrimaryKey(String stuid);

    int updateByPrimaryKeySelective(Studentbak record);

    int updateByPrimaryKey(Studentbak record);

    int updateCard(@Param("card") String card, @Param("newCard") String newCard);
}
package com.lander.basic.dao;


import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseDao<T,Q> {
    int countByExample(Q example);

    int deleteByExample(Q example);

    int deleteByPrimaryKey(Long fid);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(Q example);

    T selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") T record, @Param("example") Q example);

    int updateByExample(@Param("record") T record, @Param("example") Q example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
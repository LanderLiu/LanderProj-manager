package com.lander.sys.dao;

import com.lander.sys.pojo.TSysSet;
import com.lander.sys.pojo.TSysSetQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysSetDao {
    int countByExample(TSysSetQuery example);

    int deleteByExample(TSysSetQuery example);

    int deleteByPrimaryKey(String fid);

    int insert(TSysSet record);

    int insertSelective(TSysSet record);

    List<TSysSet> selectByExample(TSysSetQuery example);

    TSysSet selectByPrimaryKey(String fid);

    int updateByExampleSelective(@Param("record") TSysSet record, @Param("example") TSysSetQuery example);

    int updateByExample(@Param("record") TSysSet record, @Param("example") TSysSetQuery example);

    int updateByPrimaryKeySelective(TSysSet record);

    int updateByPrimaryKey(TSysSet record);
}
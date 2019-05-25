package com.lander.sale.dao;

import com.lander.sale.pojo.TSaleOrderitem;
import com.lander.sale.pojo.TSaleOrderitemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSaleOrderitemDao {
    int countByExample(TSaleOrderitemQuery example);

    int deleteByExample(TSaleOrderitemQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TSaleOrderitem record);

    int insertSelective(TSaleOrderitem record);

    List<TSaleOrderitem> selectByExample(TSaleOrderitemQuery example);

    TSaleOrderitem selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TSaleOrderitem record, @Param("example") TSaleOrderitemQuery example);

    int updateByExample(@Param("record") TSaleOrderitem record, @Param("example") TSaleOrderitemQuery example);

    int updateByPrimaryKeySelective(TSaleOrderitem record);

    int updateByPrimaryKey(TSaleOrderitem record);
}
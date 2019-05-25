package com.lander.sale.dao;

import com.lander.sale.pojo.TSaleOrder;
import com.lander.sale.pojo.TSaleOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSaleOrderDao {
    int countByExample(TSaleOrderQuery example);

    int deleteByExample(TSaleOrderQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TSaleOrder record);

    int insertSelective(TSaleOrder record);

    List<TSaleOrder> selectByExample(TSaleOrderQuery example);

    TSaleOrder selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TSaleOrder record, @Param("example") TSaleOrderQuery example);

    int updateByExample(@Param("record") TSaleOrder record, @Param("example") TSaleOrderQuery example);

    int updateByPrimaryKeySelective(TSaleOrder record);

    int updateByPrimaryKey(TSaleOrder record);
}
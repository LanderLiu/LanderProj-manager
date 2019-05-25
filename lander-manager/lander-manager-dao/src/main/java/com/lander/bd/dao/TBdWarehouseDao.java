package com.lander.bd.dao;

import com.lander.bd.pojo.TBdWarehouse;
import com.lander.bd.pojo.TBdWarehouseQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdWarehouseDao {
    int countByExample(TBdWarehouseQuery example);

    int deleteByExample(TBdWarehouseQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdWarehouse record);

    int insertSelective(TBdWarehouse record);

    List<TBdWarehouse> selectByExample(TBdWarehouseQuery example);

    TBdWarehouse selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdWarehouse record, @Param("example") TBdWarehouseQuery example);

    int updateByExample(@Param("record") TBdWarehouse record, @Param("example") TBdWarehouseQuery example);

    int updateByPrimaryKeySelective(TBdWarehouse record);

    int updateByPrimaryKey(TBdWarehouse record);
}
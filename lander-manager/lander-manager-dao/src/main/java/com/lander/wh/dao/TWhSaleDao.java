package com.lander.wh.dao;

import com.lander.wh.pojo.TWhSale;
import com.lander.wh.pojo.TWhSaleQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhSaleDao {
    int countByExample(TWhSaleQuery example);

    int deleteByExample(TWhSaleQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhSale record);

    int insertSelective(TWhSale record);

    List<TWhSale> selectByExample(TWhSaleQuery example);

    TWhSale selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhSale record, @Param("example") TWhSaleQuery example);

    int updateByExample(@Param("record") TWhSale record, @Param("example") TWhSaleQuery example);

    int updateByPrimaryKeySelective(TWhSale record);

    int updateByPrimaryKey(TWhSale record);
}
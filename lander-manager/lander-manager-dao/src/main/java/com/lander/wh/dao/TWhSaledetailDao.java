package com.lander.wh.dao;

import com.lander.wh.pojo.TWhSaledetail;
import com.lander.wh.pojo.TWhSaledetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhSaledetailDao {
    int countByExample(TWhSaledetailQuery example);

    int deleteByExample(TWhSaledetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhSaledetail record);

    int insertSelective(TWhSaledetail record);

    List<TWhSaledetail> selectByExample(TWhSaledetailQuery example);

    TWhSaledetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhSaledetail record, @Param("example") TWhSaledetailQuery example);

    int updateByExample(@Param("record") TWhSaledetail record, @Param("example") TWhSaledetailQuery example);

    int updateByPrimaryKeySelective(TWhSaledetail record);

    int updateByPrimaryKey(TWhSaledetail record);
}
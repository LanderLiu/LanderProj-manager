package com.lander.bd.dao;

import com.lander.bd.pojo.TBdSupply;
import com.lander.bd.pojo.TBdSupplyQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdSupplyDao {
    int countByExample(TBdSupplyQuery example);

    int deleteByExample(TBdSupplyQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdSupply record);

    int insertSelective(TBdSupply record);

    List<TBdSupply> selectByExample(TBdSupplyQuery example);

    TBdSupply selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdSupply record, @Param("example") TBdSupplyQuery example);

    int updateByExample(@Param("record") TBdSupply record, @Param("example") TBdSupplyQuery example);

    int updateByPrimaryKeySelective(TBdSupply record);

    int updateByPrimaryKey(TBdSupply record);
}
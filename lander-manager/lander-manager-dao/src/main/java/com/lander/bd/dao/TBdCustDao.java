package com.lander.bd.dao;

import com.lander.bd.pojo.TBdCust;
import com.lander.bd.pojo.TBdCustQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdCustDao {
    int countByExample(TBdCustQuery example);

    int deleteByExample(TBdCustQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdCust record);

    int insertSelective(TBdCust record);

    List<TBdCust> selectByExample(TBdCustQuery example);

    TBdCust selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdCust record, @Param("example") TBdCustQuery example);

    int updateByExample(@Param("record") TBdCust record, @Param("example") TBdCustQuery example);

    int updateByPrimaryKeySelective(TBdCust record);

    int updateByPrimaryKey(TBdCust record);
}
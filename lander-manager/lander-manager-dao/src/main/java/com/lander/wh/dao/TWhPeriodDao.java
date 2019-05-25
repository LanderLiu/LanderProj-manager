package com.lander.wh.dao;

import com.lander.wh.pojo.TWhPeriod;
import com.lander.wh.pojo.TWhPeriodQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhPeriodDao {
    int countByExample(TWhPeriodQuery example);

    int deleteByExample(TWhPeriodQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhPeriod record);

    int insertSelective(TWhPeriod record);

    List<TWhPeriod> selectByExample(TWhPeriodQuery example);

    TWhPeriod selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhPeriod record, @Param("example") TWhPeriodQuery example);

    int updateByExample(@Param("record") TWhPeriod record, @Param("example") TWhPeriodQuery example);

    int updateByPrimaryKeySelective(TWhPeriod record);

    int updateByPrimaryKey(TWhPeriod record);
}
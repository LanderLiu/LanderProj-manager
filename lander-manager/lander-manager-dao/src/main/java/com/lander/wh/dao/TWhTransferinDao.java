package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransferin;
import com.lander.wh.pojo.TWhTransferinQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferinDao {
    int countByExample(TWhTransferinQuery example);

    int deleteByExample(TWhTransferinQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransferin record);

    int insertSelective(TWhTransferin record);

    List<TWhTransferin> selectByExample(TWhTransferinQuery example);

    TWhTransferin selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransferin record, @Param("example") TWhTransferinQuery example);

    int updateByExample(@Param("record") TWhTransferin record, @Param("example") TWhTransferinQuery example);

    int updateByPrimaryKeySelective(TWhTransferin record);

    int updateByPrimaryKey(TWhTransferin record);
}
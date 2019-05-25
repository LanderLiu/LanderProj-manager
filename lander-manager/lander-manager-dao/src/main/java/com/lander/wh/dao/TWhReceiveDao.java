package com.lander.wh.dao;

import com.lander.wh.pojo.TWhReceive;
import com.lander.wh.pojo.TWhReceiveQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhReceiveDao {
    int countByExample(TWhReceiveQuery example);

    int deleteByExample(TWhReceiveQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhReceive record);

    int insertSelective(TWhReceive record);

    List<TWhReceive> selectByExample(TWhReceiveQuery example);

    TWhReceive selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhReceive record, @Param("example") TWhReceiveQuery example);

    int updateByExample(@Param("record") TWhReceive record, @Param("example") TWhReceiveQuery example);

    int updateByPrimaryKeySelective(TWhReceive record);

    int updateByPrimaryKey(TWhReceive record);
}
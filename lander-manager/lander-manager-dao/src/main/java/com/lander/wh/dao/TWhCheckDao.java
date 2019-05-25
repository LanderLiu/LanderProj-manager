package com.lander.wh.dao;

import com.lander.wh.pojo.TWhCheck;
import com.lander.wh.pojo.TWhCheckQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhCheckDao {
    int countByExample(TWhCheckQuery example);

    int deleteByExample(TWhCheckQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhCheck record);

    int insertSelective(TWhCheck record);

    List<TWhCheck> selectByExample(TWhCheckQuery example);

    TWhCheck selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhCheck record, @Param("example") TWhCheckQuery example);

    int updateByExample(@Param("record") TWhCheck record, @Param("example") TWhCheckQuery example);

    int updateByPrimaryKeySelective(TWhCheck record);

    int updateByPrimaryKey(TWhCheck record);
}
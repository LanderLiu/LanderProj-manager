package com.lander.wh.dao;

import com.lander.wh.pojo.TWhOtherin;
import com.lander.wh.pojo.TWhOtherinQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhOtherinDao {
    int countByExample(TWhOtherinQuery example);

    int deleteByExample(TWhOtherinQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhOtherin record);

    int insertSelective(TWhOtherin record);

    List<TWhOtherin> selectByExample(TWhOtherinQuery example);

    TWhOtherin selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhOtherin record, @Param("example") TWhOtherinQuery example);

    int updateByExample(@Param("record") TWhOtherin record, @Param("example") TWhOtherinQuery example);

    int updateByPrimaryKeySelective(TWhOtherin record);

    int updateByPrimaryKey(TWhOtherin record);
}
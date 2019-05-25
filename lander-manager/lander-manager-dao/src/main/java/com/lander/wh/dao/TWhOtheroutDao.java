package com.lander.wh.dao;

import com.lander.wh.pojo.TWhOtherout;
import com.lander.wh.pojo.TWhOtheroutQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhOtheroutDao {
    int countByExample(TWhOtheroutQuery example);

    int deleteByExample(TWhOtheroutQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhOtherout record);

    int insertSelective(TWhOtherout record);

    List<TWhOtherout> selectByExample(TWhOtheroutQuery example);

    TWhOtherout selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhOtherout record, @Param("example") TWhOtheroutQuery example);

    int updateByExample(@Param("record") TWhOtherout record, @Param("example") TWhOtheroutQuery example);

    int updateByPrimaryKeySelective(TWhOtherout record);

    int updateByPrimaryKey(TWhOtherout record);
}
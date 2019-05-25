package com.lander.wh.dao;

import com.lander.wh.pojo.TWhPicking;
import com.lander.wh.pojo.TWhPickingQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhPickingDao {
    int countByExample(TWhPickingQuery example);

    int deleteByExample(TWhPickingQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhPicking record);

    int insertSelective(TWhPicking record);

    List<TWhPicking> selectByExample(TWhPickingQuery example);

    TWhPicking selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhPicking record, @Param("example") TWhPickingQuery example);

    int updateByExample(@Param("record") TWhPicking record, @Param("example") TWhPickingQuery example);

    int updateByPrimaryKeySelective(TWhPicking record);

    int updateByPrimaryKey(TWhPicking record);
}
package com.lander.wh.dao;

import com.lander.wh.pojo.TWhPickingdetail;
import com.lander.wh.pojo.TWhPickingdetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhPickingdetailDao {
    int countByExample(TWhPickingdetailQuery example);

    int deleteByExample(TWhPickingdetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhPickingdetail record);

    int insertSelective(TWhPickingdetail record);

    List<TWhPickingdetail> selectByExample(TWhPickingdetailQuery example);

    TWhPickingdetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhPickingdetail record, @Param("example") TWhPickingdetailQuery example);

    int updateByExample(@Param("record") TWhPickingdetail record, @Param("example") TWhPickingdetailQuery example);

    int updateByPrimaryKeySelective(TWhPickingdetail record);

    int updateByPrimaryKey(TWhPickingdetail record);
}
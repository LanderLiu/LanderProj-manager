package com.lander.wh.dao;

import com.lander.wh.pojo.TWhCheckdetail;
import com.lander.wh.pojo.TWhCheckdetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhCheckdetailDao {
    int countByExample(TWhCheckdetailQuery example);

    int deleteByExample(TWhCheckdetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhCheckdetail record);

    int insertSelective(TWhCheckdetail record);

    List<TWhCheckdetail> selectByExample(TWhCheckdetailQuery example);

    TWhCheckdetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhCheckdetail record, @Param("example") TWhCheckdetailQuery example);

    int updateByExample(@Param("record") TWhCheckdetail record, @Param("example") TWhCheckdetailQuery example);

    int updateByPrimaryKeySelective(TWhCheckdetail record);

    int updateByPrimaryKey(TWhCheckdetail record);
}
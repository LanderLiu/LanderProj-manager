package com.lander.wh.dao;

import com.lander.wh.pojo.TWhReceivedetail;
import com.lander.wh.pojo.TWhReceivedetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhReceivedetailDao {
    int countByExample(TWhReceivedetailQuery example);

    int deleteByExample(TWhReceivedetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhReceivedetail record);

    int insertSelective(TWhReceivedetail record);

    List<TWhReceivedetail> selectByExample(TWhReceivedetailQuery example);

    TWhReceivedetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhReceivedetail record, @Param("example") TWhReceivedetailQuery example);

    int updateByExample(@Param("record") TWhReceivedetail record, @Param("example") TWhReceivedetailQuery example);

    int updateByPrimaryKeySelective(TWhReceivedetail record);

    int updateByPrimaryKey(TWhReceivedetail record);
}
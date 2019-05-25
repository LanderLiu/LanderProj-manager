package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransferout;
import com.lander.wh.pojo.TWhTransferoutQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferoutDao {
    int countByExample(TWhTransferoutQuery example);

    int deleteByExample(TWhTransferoutQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransferout record);

    int insertSelective(TWhTransferout record);

    List<TWhTransferout> selectByExample(TWhTransferoutQuery example);

    TWhTransferout selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransferout record, @Param("example") TWhTransferoutQuery example);

    int updateByExample(@Param("record") TWhTransferout record, @Param("example") TWhTransferoutQuery example);

    int updateByPrimaryKeySelective(TWhTransferout record);

    int updateByPrimaryKey(TWhTransferout record);
}
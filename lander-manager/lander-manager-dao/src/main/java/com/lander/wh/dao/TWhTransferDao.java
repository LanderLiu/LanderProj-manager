package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransfer;
import com.lander.wh.pojo.TWhTransferQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferDao {
    int countByExample(TWhTransferQuery example);

    int deleteByExample(TWhTransferQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransfer record);

    int insertSelective(TWhTransfer record);

    List<TWhTransfer> selectByExample(TWhTransferQuery example);

    TWhTransfer selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransfer record, @Param("example") TWhTransferQuery example);

    int updateByExample(@Param("record") TWhTransfer record, @Param("example") TWhTransferQuery example);

    int updateByPrimaryKeySelective(TWhTransfer record);

    int updateByPrimaryKey(TWhTransfer record);
}
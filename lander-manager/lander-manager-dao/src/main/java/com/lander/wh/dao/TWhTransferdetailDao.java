package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransferdetail;
import com.lander.wh.pojo.TWhTransferdetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferdetailDao {
    int countByExample(TWhTransferdetailQuery example);

    int deleteByExample(TWhTransferdetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransferdetail record);

    int insertSelective(TWhTransferdetail record);

    List<TWhTransferdetail> selectByExample(TWhTransferdetailQuery example);

    TWhTransferdetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransferdetail record, @Param("example") TWhTransferdetailQuery example);

    int updateByExample(@Param("record") TWhTransferdetail record, @Param("example") TWhTransferdetailQuery example);

    int updateByPrimaryKeySelective(TWhTransferdetail record);

    int updateByPrimaryKey(TWhTransferdetail record);
}
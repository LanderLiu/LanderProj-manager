package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransferoutdetail;
import com.lander.wh.pojo.TWhTransferoutdetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferoutdetailDao {
    int countByExample(TWhTransferoutdetailQuery example);

    int deleteByExample(TWhTransferoutdetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransferoutdetail record);

    int insertSelective(TWhTransferoutdetail record);

    List<TWhTransferoutdetail> selectByExample(TWhTransferoutdetailQuery example);

    TWhTransferoutdetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransferoutdetail record, @Param("example") TWhTransferoutdetailQuery example);

    int updateByExample(@Param("record") TWhTransferoutdetail record, @Param("example") TWhTransferoutdetailQuery example);

    int updateByPrimaryKeySelective(TWhTransferoutdetail record);

    int updateByPrimaryKey(TWhTransferoutdetail record);
}
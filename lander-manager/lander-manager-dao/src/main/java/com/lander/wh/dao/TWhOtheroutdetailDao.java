package com.lander.wh.dao;

import com.lander.wh.pojo.TWhOtheroutdetail;
import com.lander.wh.pojo.TWhOtheroutdetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhOtheroutdetailDao {
    int countByExample(TWhOtheroutdetailQuery example);

    int deleteByExample(TWhOtheroutdetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhOtheroutdetail record);

    int insertSelective(TWhOtheroutdetail record);

    List<TWhOtheroutdetail> selectByExample(TWhOtheroutdetailQuery example);

    TWhOtheroutdetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhOtheroutdetail record, @Param("example") TWhOtheroutdetailQuery example);

    int updateByExample(@Param("record") TWhOtheroutdetail record, @Param("example") TWhOtheroutdetailQuery example);

    int updateByPrimaryKeySelective(TWhOtheroutdetail record);

    int updateByPrimaryKey(TWhOtheroutdetail record);
}
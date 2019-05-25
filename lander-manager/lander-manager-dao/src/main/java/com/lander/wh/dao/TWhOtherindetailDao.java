package com.lander.wh.dao;

import com.lander.wh.pojo.TWhOtherindetail;
import com.lander.wh.pojo.TWhOtherindetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhOtherindetailDao {
    int countByExample(TWhOtherindetailQuery example);

    int deleteByExample(TWhOtherindetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhOtherindetail record);

    int insertSelective(TWhOtherindetail record);

    List<TWhOtherindetail> selectByExample(TWhOtherindetailQuery example);

    TWhOtherindetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhOtherindetail record, @Param("example") TWhOtherindetailQuery example);

    int updateByExample(@Param("record") TWhOtherindetail record, @Param("example") TWhOtherindetailQuery example);

    int updateByPrimaryKeySelective(TWhOtherindetail record);

    int updateByPrimaryKey(TWhOtherindetail record);
}
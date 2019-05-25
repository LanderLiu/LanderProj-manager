package com.lander.wh.dao;

import com.lander.wh.pojo.TWhTransferindetail;
import com.lander.wh.pojo.TWhTransferindetailQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhTransferindetailDao {
    int countByExample(TWhTransferindetailQuery example);

    int deleteByExample(TWhTransferindetailQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhTransferindetail record);

    int insertSelective(TWhTransferindetail record);

    List<TWhTransferindetail> selectByExample(TWhTransferindetailQuery example);

    TWhTransferindetail selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhTransferindetail record, @Param("example") TWhTransferindetailQuery example);

    int updateByExample(@Param("record") TWhTransferindetail record, @Param("example") TWhTransferindetailQuery example);

    int updateByPrimaryKeySelective(TWhTransferindetail record);

    int updateByPrimaryKey(TWhTransferindetail record);
}
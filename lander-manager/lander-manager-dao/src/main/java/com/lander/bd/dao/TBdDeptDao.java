package com.lander.bd.dao;

import com.lander.bd.pojo.TBdDept;
import com.lander.bd.pojo.TBdDeptQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdDeptDao {
    int countByExample(TBdDeptQuery example);

    int deleteByExample(TBdDeptQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdDept record);

    int insertSelective(TBdDept record);

    List<TBdDept> selectByExample(TBdDeptQuery example);

    TBdDept selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdDept record, @Param("example") TBdDeptQuery example);

    int updateByExample(@Param("record") TBdDept record, @Param("example") TBdDeptQuery example);

    int updateByPrimaryKeySelective(TBdDept record);

    int updateByPrimaryKey(TBdDept record);
}
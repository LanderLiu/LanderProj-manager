package com.lander.pr.dao;

import com.lander.pr.pojo.TPrOrderitem;
import com.lander.pr.pojo.TPrOrderitemQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPrOrderitemDao {
    int countByExample(TPrOrderitemQuery example);

    int deleteByExample(TPrOrderitemQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TPrOrderitem record);

    int insertSelective(TPrOrderitem record);

    List<TPrOrderitem> selectByExample(TPrOrderitemQuery example);

    TPrOrderitem selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TPrOrderitem record, @Param("example") TPrOrderitemQuery example);

    int updateByExample(@Param("record") TPrOrderitem record, @Param("example") TPrOrderitemQuery example);

    int updateByPrimaryKeySelective(TPrOrderitem record);

    int updateByPrimaryKey(TPrOrderitem record);
}
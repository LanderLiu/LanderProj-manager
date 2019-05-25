package com.lander.sys.dao;

import com.lander.sys.pojo.TSysControlleraction;
import com.lander.sys.pojo.TSysControlleractionQuery;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysControlleractionDao {
    int countByExample(TSysControlleractionQuery example);

    int deleteByExample(TSysControlleractionQuery example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TSysControlleraction record);

    int insertSelective(TSysControlleraction record);

    List<TSysControlleraction> selectByExample(TSysControlleractionQuery example);

    TSysControlleraction selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TSysControlleraction record, @Param("example") TSysControlleractionQuery example);

    int updateByExample(@Param("record") TSysControlleraction record, @Param("example") TSysControlleractionQuery example);

    int updateByPrimaryKeySelective(TSysControlleraction record);

    int updateByPrimaryKey(TSysControlleraction record);
}
package com.lander.sys.dao;

import com.lander.sys.pojo.TSysRoleright;
import com.lander.sys.pojo.TSysRolerightQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysRolerightDao {
    int countByExample(TSysRolerightQuery example);

    int deleteByExample(TSysRolerightQuery example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TSysRoleright record);

    int insertSelective(TSysRoleright record);

    List<TSysRoleright> selectByExample(TSysRolerightQuery example);

    TSysRoleright selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TSysRoleright record, @Param("example") TSysRolerightQuery example);

    int updateByExample(@Param("record") TSysRoleright record, @Param("example") TSysRolerightQuery example);

    int updateByPrimaryKeySelective(TSysRoleright record);

    int updateByPrimaryKey(TSysRoleright record);
}
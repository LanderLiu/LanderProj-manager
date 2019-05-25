package com.lander.sys.dao;

import com.lander.sys.pojo.TSysRole;
import com.lander.sys.pojo.TSysRoleQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysRoleDao {
    int countByExample(TSysRoleQuery example);

    int deleteByExample(TSysRoleQuery example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TSysRole record);

    int insertSelective(TSysRole record);

    List<TSysRole> selectByExample(TSysRoleQuery example);

    TSysRole selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TSysRole record, @Param("example") TSysRoleQuery example);

    int updateByExample(@Param("record") TSysRole record, @Param("example") TSysRoleQuery example);

    int updateByPrimaryKeySelective(TSysRole record);

    int updateByPrimaryKey(TSysRole record);
}
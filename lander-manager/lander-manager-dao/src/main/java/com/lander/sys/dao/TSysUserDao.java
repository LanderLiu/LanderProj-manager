package com.lander.sys.dao;

import com.lander.sys.pojo.TSysUser;
import com.lander.sys.pojo.TSysUserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysUserDao {
    int countByExample(TSysUserQuery example);

    int deleteByExample(TSysUserQuery example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TSysUser record);

    int insertSelective(TSysUser record);

    List<TSysUser> selectByExample(TSysUserQuery example);

    TSysUser selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TSysUser record, @Param("example") TSysUserQuery example);

    int updateByExample(@Param("record") TSysUser record, @Param("example") TSysUserQuery example);

    int updateByPrimaryKeySelective(TSysUser record);

    int updateByPrimaryKey(TSysUser record);
}
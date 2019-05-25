package com.lander.sys.dao;

import com.lander.sys.pojo.TSysRoleuser;
import com.lander.sys.pojo.TSysRoleuserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSysRoleuserDao {
    int countByExample(TSysRoleuserQuery example);

    int deleteByExample(TSysRoleuserQuery example);

    int deleteByPrimaryKey(Integer fid);

    int insert(TSysRoleuser record);

    int insertSelective(TSysRoleuser record);

    List<TSysRoleuser> selectByExample(TSysRoleuserQuery example);

    TSysRoleuser selectByPrimaryKey(Integer fid);

    int updateByExampleSelective(@Param("record") TSysRoleuser record, @Param("example") TSysRoleuserQuery example);

    int updateByExample(@Param("record") TSysRoleuser record, @Param("example") TSysRoleuserQuery example);

    int updateByPrimaryKeySelective(TSysRoleuser record);

    int updateByPrimaryKey(TSysRoleuser record);
}
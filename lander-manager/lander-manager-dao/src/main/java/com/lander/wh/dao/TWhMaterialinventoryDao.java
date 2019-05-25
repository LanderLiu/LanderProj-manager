package com.lander.wh.dao;

import com.lander.wh.pojo.TWhMaterialinventory;
import com.lander.wh.pojo.TWhMaterialinventoryQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TWhMaterialinventoryDao {
    int countByExample(TWhMaterialinventoryQuery example);

    int deleteByExample(TWhMaterialinventoryQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TWhMaterialinventory record);

    int insertSelective(TWhMaterialinventory record);

    List<TWhMaterialinventory> selectByExample(TWhMaterialinventoryQuery example);

    TWhMaterialinventory selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TWhMaterialinventory record, @Param("example") TWhMaterialinventoryQuery example);

    int updateByExample(@Param("record") TWhMaterialinventory record, @Param("example") TWhMaterialinventoryQuery example);

    int updateByPrimaryKeySelective(TWhMaterialinventory record);

    int updateByPrimaryKey(TWhMaterialinventory record);
}
package com.lander.bd.dao;

import com.lander.bd.pojo.TBdMaterial;
import com.lander.bd.pojo.TBdMaterialQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdMaterialDao {
    int countByExample(TBdMaterialQuery example);

    int deleteByExample(TBdMaterialQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdMaterial record);

    int insertSelective(TBdMaterial record);

    List<TBdMaterial> selectByExample(TBdMaterialQuery example);

    TBdMaterial selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdMaterial record, @Param("example") TBdMaterialQuery example);

    int updateByExample(@Param("record") TBdMaterial record, @Param("example") TBdMaterialQuery example);

    int updateByPrimaryKeySelective(TBdMaterial record);

    int updateByPrimaryKey(TBdMaterial record);
}
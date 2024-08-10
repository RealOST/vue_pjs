package com.bd.mapper;

import com.bd.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    //查询所有
    @Select("select * from brand")
    @ResultMap("brandMap")
    List<Brand> selectAll();

    @Insert("insert into brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    @Select("select * from brand where id=#{id}")
    @ResultMap("brandMap")
    Brand selectById(int id);

    //修改
    @Update("update brand set brand_name = #{brandName},company_name = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    @ResultMap("brandMap")
    void update(Brand brand);

    @Delete("delete from brand where id=#{id}")
    void delete(int id);

    void deleteByIds(@Param("ids") int[] ids);

    //分页查询
    @Select("select * from brand limit #{begin} , #{size}")
    @ResultMap("brandMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    //查询总记录数
    @Select("select count(*) from brand ")
    int selectTotalCount();

    //分页条件查询
    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size")
    int size,@Param("brand") Brand brand);
    //根据条件查询总记录数
    int selectTotalCountByCondition(Brand brand);
}

package com.bd.service;

import com.bd.pojo.Brand;
import com.bd.pojo.PageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();

    void add(Brand brand);

    Brand selectById(int id);

    void update(Brand brand);

    void delete(int id);

    void deleteByIds(int[] ids);

    //分页查询
    PageBean<Brand> selectByPage(int currentPage, int pageSize);

    //分页条件查询
    PageBean<Brand>  selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}

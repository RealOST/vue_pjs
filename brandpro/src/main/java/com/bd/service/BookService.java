package com.bd.service;

import com.bd.pojo.Book;
import com.bd.pojo.PageBean;

import java.util.List;

public interface BookService {
    List<Book> selectAll();

    void add(Book book);

    Book selectById(int id);

    void update(Book book);

    void delete(int id);

    void deleteByIds(int[] ids);

    //分页查询
    PageBean<Book> selectByPage(int currentPage, int pageSize);

    //分页条件查询
    PageBean<Book>  selectByPACBook(int currentPage,int pageSize,Book book);
}

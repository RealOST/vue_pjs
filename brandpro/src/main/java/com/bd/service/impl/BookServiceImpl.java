package com.bd.service.impl;

import com.bd.mapper.BookMapper;
import com.bd.pojo.Book;
import com.bd.pojo.PageBean;
import com.bd.service.BookService;
import com.bd.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BookServiceImpl implements BookService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    public List<Book> selectAll(){
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        List<Book> books = mapper.selectAll();
        sqlSession.close();
        return books;
    }

    public void add(Book book){
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.add(book);
        sqlSession.commit();//增删改必须提交sqlSession
        sqlSession.close();
    }

    public Book selectById(int id){
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        Book book = mapper.selectById(id);
        sqlSession.close();
        return book;
    }

    public void update(Book book){
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.update(book);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(int id){
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        mapper.delBooks(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Book> selectByPage(int currentPage, int pageSize) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BookMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;
        //5. 查询当前页数据
        List<Book> rows = mapper.selectByPage(begin, size);
        //6. 查询总记录数
        int totalCount = mapper.selectTotalCount();
        //7. 封装PageBean对象
        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //8. 释放资源
        sqlSession.close();
        return pageBean;
    }

    public PageBean<Book> selectByPACBook(int currentPage, int
            pageSize, Book book) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //3. 获取BookMapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //4. 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        // 计算查询条目数
        int size = pageSize;
        // 处理book条件，模糊表达式
        String bookName = book.getBookName();
        if (bookName != null && bookName.length() > 0) {
            book.setBookName("%" + bookName + "%");
        }
        //5. 查询当前页数据
        List<Book> rows = mapper.selectByPACBook(begin, size, book);
//        System.out.println(rows);
        //6. 查询总记录数
        int totalCount = mapper.selectTotalCountByCBook(book);
//        System.out.println(totalCount);
        //7. 封装PageBean对象
        PageBean<Book> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //8. 释放资源
        sqlSession.close();
        return pageBean;
    }
}

package com.bd.mapper;

import com.bd.pojo.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BookMapper {
    //查询所有
    @Select("select * from books")
    List<Book> selectAll();

    @Insert("insert into books values(null,#{bookName},#{bookCounts},#{detail})")
    void add(Book book);

    @Select("select * from books where bookID=#{id}")
    Book selectById(int id);

    //修改
    @Update("update books set bookName = #{bookName},bookCounts = #{bookCounts},detail = #{detail} where bookID = #{bookID}")
    void update(Book book);

    @Delete("delete from books where bookID=#{id}")
    void delete(int id);

    void delBooks(@Param("ids") int[] ids);

    //分页查询
    @Select("select * from books limit #{begin} , #{size}")
    List<Book> selectByPage(@Param("begin") int begin,@Param("size") int size);

    //查询总记录数
    @Select("select count(*) from books ")
    int selectTotalCount();

    //分页条件查询
    List<Book> selectByPACBook(@Param("begin") int begin,@Param("size")
    int size,@Param("book") Book book);
    //根据条件查询总记录数
    int selectTotalCountByCBook(Book book);
}

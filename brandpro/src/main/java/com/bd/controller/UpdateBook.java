package com.bd.controller;

import com.alibaba.fastjson.JSON;
import com.bd.pojo.Book;
import com.bd.service.BookService;
import com.bd.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/updateBook")
public class UpdateBook extends HttpServlet {
    private BookService service = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST请求的乱码问题
        req.setCharacterEncoding("utf-8");//解决输入流乱码
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //转为 Book
        Book book = JSON.parseObject(params, Book.class);
        //2. 调用service 完成添加
        service.update(book);
        //3. 响应成功的标识
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

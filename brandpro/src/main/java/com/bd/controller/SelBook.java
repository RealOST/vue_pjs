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
import java.io.IOException;

@WebServlet("/selBook")
public class SelBook extends HttpServlet {
    private BookService service = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        System.out.println("获取id："+id);
        Book book = service.selectById(Integer.parseInt(id));
        String jsonString = JSON.toJSONString(book);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

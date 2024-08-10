package com.bd.controller;

import com.alibaba.fastjson.JSON;
import com.bd.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/delBooks")
public class DelBooks extends HttpServlet {
    BookServiceImpl service = new BookServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收数据 json  [1,2,3]
        req.setCharacterEncoding("utf-8");
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);
        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println(Arrays.toString(ids));
        //2. 调用service添加
        service.deleteByIds(ids);
        //3. 响应成功的标识
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

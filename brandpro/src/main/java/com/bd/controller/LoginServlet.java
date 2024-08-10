package com.bd.controller;


import com.alibaba.fastjson.JSON;
import com.bd.pojo.User;
import com.bd.service.UserService;
import com.bd.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");//解决输入流乱码
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        User user = JSON.parseObject(params, User.class);
        User _user = service.login(user);
        if (_user != null){
            resp.getWriter().write("success");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}

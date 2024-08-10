package com.bd.controller;

import com.alibaba.fastjson.JSON;
import com.bd.pojo.Brand;
import com.bd.service.BrandService;
import com.bd.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/selById")
public class SelectByIdServlet extends HttpServlet {
    private BrandService service = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
//        System.out.println("获取id："+id);
        Brand brand = service.selectById(Integer.parseInt(id));
        String jsonString = JSON.toJSONString(brand);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}

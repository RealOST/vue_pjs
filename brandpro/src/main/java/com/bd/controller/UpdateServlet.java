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
import java.io.BufferedReader;
import java.io.IOException;
@WebServlet("/updateBrand")
public class UpdateServlet extends HttpServlet {
    private BrandService service = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //处理POST请求的乱码问题
        req.setCharacterEncoding("utf-8");//解决输入流乱码
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);
        //2. 调用service 完成添加
        service.update(brand);
        //3. 响应成功的标识
        resp.getWriter().write("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

package com.bd.controller;

import com.alibaba.fastjson.JSON;
import com.bd.pojo.Brand;
import com.bd.pojo.PageBean;
import com.bd.service.BrandService;
import com.bd.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/selectByPACBrand")
public class SelectByPACServlet extends HttpServlet {
    private BrandService service = new BrandServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);
        // 获取查询条件对象
        req.setCharacterEncoding("utf-8");//解决输入流乱码
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);
        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);
        System.out.println(brand);
        //2. 调用service查询
        PageBean<Brand> pageBean = service.selectByPageAndCondition(currentPage,pageSize,brand);
        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}

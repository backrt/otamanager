package com.jettyserver.servlet;

import com.jettyserver.servlet.util.CommonUtil;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：lhb on 2017/11/10 15:12
 * Mail：lihaibo@znds.com
 */

public class TestServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        CommonUtil.renderHtml(resp,"测试网页");
    }
}

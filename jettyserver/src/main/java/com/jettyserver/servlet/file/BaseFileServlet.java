package com.jettyserver.servlet.file;

import com.jettyserver.servlet.BaseServlet;
import com.jettyserver.servlet.util.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author：lhb on 2017/11/10 14:58
 * Mail：lihaibo@znds.com
 */
public abstract class BaseFileServlet extends BaseServlet {

    /**
     * 定位文件
     *
     * @return
     */
    public abstract File directionFile(HttpServletRequest req);


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        this.doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);

        File file = directionFile(req);
        if (file != null
                &&
                file.exists()) {
            long length = file.length();
            resp.setContentLength((int) length);
            OutputStream out = resp.getOutputStream();
            FileInputStream stream = new FileInputStream(file);
            StreamUtil.fileStream(stream, out);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}

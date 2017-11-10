package com.jettyserver.servlet.file;


import java.io.File;

import javax.servlet.http.HttpServletRequest;

public class ImageServlet extends BaseFileServlet {
    @Override
    public File directionFile(HttpServletRequest req) {
//        String name = req.getParameter("name");
//        File file = ImageUtil.getAppIconFile(App.getContext(), name);
        return null;
    }
}


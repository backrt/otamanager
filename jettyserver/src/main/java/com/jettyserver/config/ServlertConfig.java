package com.jettyserver.config;

import com.jettyserver.servlet.TestServlet;
import com.jettyserver.servlet.file.ImageServlet;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class ServlertConfig {

    private static final String DOMAIN = "/test";
    private static final String IMAGE = "/image";
    private static final String APK = "/apk";


    public static void config(ServletContextHandler handler) {

        handler.addServlet(new ServletHolder(new TestServlet()), DOMAIN);
        handler.addServlet(new ServletHolder(new ImageServlet()), DOMAIN + IMAGE);
    }
}

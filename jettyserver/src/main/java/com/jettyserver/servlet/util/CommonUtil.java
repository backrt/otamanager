package com.jettyserver.servlet.util;


import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;


public class CommonUtil {
    //header 常量定义//
    private static final String ENCODING_PREFIX = "encoding";
    private static final String NOCACHE_PREFIX = "no-cache";
    private static final String ENCODING_DEFAULT = "UTF-8"; //
    private static final boolean NOCACHE_DEFAULT = true;

    //content-type 定义 //
    private static final String TEXT = "text/plain";
    private static final String JSON = "application/json";
    private static final String XML = "text/xml";
    private static final String HTML = "text/html";


    /**
     * 直接输出内容的简便函数.
     * <p>
     * eg.
     * render("text/plain", "hello", "encoding:GBK");
     * render("text/plain", "hello", "no-cache:false");
     * render("text/plain", "hello", "encoding:GBK", "no-cache:false");
     *
     * @param headers 可变的header数组，目前接受的值为"encoding:"或"no-cache:",默认值分别为UTF-8和true.
     */
    public static void render(final HttpServletResponse response, final String contentType, final String content, final String... headers) {
        try {
            //分析headers参数
            String encoding = ENCODING_DEFAULT;
            boolean noCache = NOCACHE_DEFAULT;
//            for (String header : headers) {
//                String headerName = StringUtils.substringBefore(header, ":");
//                String headerValue = StringUtils.substringAfter(header, ":");
//
//                if (StringUtils.equalsIgnoreCase(headerName, ENCODING_PREFIX)) {
//                    encoding = headerValue;
//                } else if (StringUtils.equalsIgnoreCase(headerName, NOCACHE_PREFIX)) {
//                    noCache = Boolean.parseBoolean(headerValue);
//                } else
//                    throw new IllegalArgumentException(headerName + "不是一个合法的header类型");
//            }

            //设置headers参数
            String fullContentType = contentType + ";charset=" + encoding;
            response.setContentType(fullContentType);
            if (noCache) {
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
            }

            PrintWriter writer = response.getWriter();
            writer.write(content);
            writer.flush();
            writer.close();
        } catch (IOException e) {
        }
    }

    /**
     * 直接输出文本.
     */
    public static void renderText(final HttpServletResponse response, final String text, final String... headers) {
        render(response, TEXT, text, headers);
    }

    /**
     * 直接输出HTML.
     */
    public static void renderHtml(final HttpServletResponse response, final String html, final String... headers) {
        render(response, HTML, html, headers);
    }

    /**
     * 直接输出XML.
     */
    public static void renderXml(final HttpServletResponse response, final String xml, final String... headers) {
        render(response, XML, xml, headers);
    }


    /**
     * @param response
     * @param jsonString
     * @param headers
     */
    public static void renderJson(final HttpServletResponse response, final String jsonString, final String... headers) {
        render(response, JSON, jsonString, headers);
    }

    /**
     * 直接输出JSON.
     *
     * @param object Java对象,将被转化为json字符串.
     */
    public static void renderJson(final HttpServletResponse response, final Object object, final String... headers) {
        renderJson(response, new GsonBuilder().create().toJson(object), headers);
    }


    /**
     * 直接输出内容与转发
     *
     * @param rep
     * @param message
     * @param headers
     */
    public static void renderScript(final HttpServletResponse rep, final String message, final String... headers) {
        PrintWriter printer = null;
        try {
            rep.setContentType("text/html;charset=UTF-8");
            printer = rep.getWriter();
            printer.write("<script language = 'javascript'>");
            printer.write("alert('" + message + "'),");
            printer.write("window.history.go(-1)");
            printer.write("</script>");
        } catch (IOException e) {
            System.err.println(e.getLocalizedMessage());
        } finally {
            if (printer != null) {
                printer.close();
            }
        }
    }

}
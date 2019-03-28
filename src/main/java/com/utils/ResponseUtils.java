package com.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtils {
    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        try {
            response.getWriter().write(text);
        } catch (IOException localIOException) {

        }
    }

    public static void outputJson(HttpServletResponse response, Object obj) {
        String s = JsonWriter.toJson(obj, false);
        response.setContentType("text/plain;charset=UTF-8");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0L);
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package com.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.utils.HttpUtils;

/**
 * @ProjectName: ehrMidware
 * @Package: com.test
 * @Author: longhai
 * @CreateDate: 2018/6/1 15:27
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class Test {


    public static void main(String[] args) {
        getToken();
    }
    public static String getToken() {
        String url = "https://poly.idpsso.net:8040/oauth/token?client_id=054698b510de19dca504049dfb4ef295V9Ly8RuVgnA&client_secret=6tBfNt1LUaHTDopN7YYBriNeA9QF5htFQR58iS563a&scope=read&grant_type=client_credentials";

        String token = HttpUtils.doPost(url,"utf-8");
        token = JSONObject.parseObject(token).getString("access_token");
        System.out.println(token);

        return token;
    }
}

package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.API;
import com.api.BaseUrl;
import com.api.entity.StuffEntity;
import com.dao.StuffDao;
import com.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @ProjectName: ehrMidware
 * @Package: com.service
 * @Author: longhai
 * @CreateDate: 2018/5/21 15:45
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service("stuffService")
public class StuffService {

    @Autowired
    StuffDao stuffDao;

    public int syncAllStuff() {
        List<StuffEntity> list=null;
        //1.查询EHR数据库数据
        try{
            list =stuffDao.selectStuffList();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        //2.测试连接主数据平台 清理数据库做全量同步
        try{
            String url = BaseUrl.HOST+ API.testConn;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0){
                return 0;
            }else {
                url = BaseUrl.HOST+ API.archiveAllStuff;
                res = HttpUtils.doGet(url);
                j = JSON.parseObject(res);
                if(j.getInteger("code") == 0){
                    return 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //3.同步数据
        try{
            JSONObject json = new JSONObject();
            json.put("list",list);
            String url = BaseUrl.HOST+ API.stuffInterestSave;
            String res = HttpUtils.doPost(url,json,"utf-8");
            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0){
                return 0;
            }else {
                url = BaseUrl.HOST+ API.stuffReset;
                res = HttpUtils.doGet(url);
                j = JSON.parseObject(res);
                if(j.getInteger("code") == 0){
                    return 0;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public int syncInfluencedStuff() {

        List<StuffEntity> list,listK=null;
        String influnencedDate=null;
        //1.连接主数据平台 获取上次同步时间 influnenced Date
        try{
            String url = BaseUrl.HOST+ API.influnencedDate;
            String res = HttpUtils.doGet(url);

            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0){
                return 0;
            }else {
                influnencedDate=j.getString("data");
            }
//            else {
//                url = BaseUrl.HOST+ API.archiveAllOrganization;
//                res = HttpUtils.doGet(url);
//                j = JSON.parseObject(res);
//                if(j.getInteger("code") == 0){
//                    return 0;
//                }
//            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //2.查询EHR数据库数据 增量数据 增删改 时间戳 > influnencedDate
        try{
            System.out.println("influnencedDate="+influnencedDate);
            list =stuffDao.selectInfluencedStuffList(influnencedDate);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //3.同步数据
        try{
            JSONObject json = new JSONObject();
            json.put("list",list);
            json.put("listK",listK);
            String url = BaseUrl.HOST+ API.stuffInfluencedSave;
            String res = HttpUtils.doPost(url,json,"utf-8");
            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0){
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
        return 0;





    }
}

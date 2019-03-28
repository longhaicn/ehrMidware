package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.API;
import com.api.BaseUrl;
import com.api.entity.StuffEntity;
import com.dao.StuffDao;
import com.utils.HttpUtils;
import com.utils.LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("stuffService")
public class StuffService {
    @Autowired
    StuffDao stuffDao;

    public int syncAllStuff() {
        List<StuffEntity> list = null;
        try {
            list = this.stuffDao.selectStuffList();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            String url = BaseUrl.HOST + API.testConn;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            url = BaseUrl.HOST + API.archiveAllStuff;
            res = HttpUtils.doGet(url);
            j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("list", list);
            String url = BaseUrl.HOST + API.stuffInterestSave;
            String res = HttpUtils.doPost(url, json, "utf-8");
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            url = BaseUrl.HOST + API.stuffReset;
            res = HttpUtils.doGet(url);
            j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public int syncInfluencedStuff() {
        List<StuffEntity> list = null;
        String influnencedDate = null;
        try {
            String url = BaseUrl.HOST + API.influnencedStfDate;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            influnencedDate = j.getString("data");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            LOG.print("influnencedDate=" + influnencedDate);
            list = this.stuffDao.selectInfluencedStuffList(influnencedDate);
            LOG.print("syncInfluencedStuff size:" + list.size());
            if (0 == list.size()) {
                LOG.print("syncInfluencedStuff abort!");
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("list", list);
            String url = BaseUrl.HOST + API.stuffInfluencedSave;
            String res = HttpUtils.doPost(url, json, "utf-8");
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            LOG.print("syncInfluencedStuff done!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public HashMap<String, String> checkValues(String email, String pinyinName, String phoneNumber) {
        HashMap<String, String> map = new HashMap();

        String emailFlag = HttpUtils.doPost(BaseUrl.OA_HOST + API.email + email, "utf-8");
        String phoneNumberFlag = HttpUtils.doPost(BaseUrl.OA_HOST + API.phoneNumber + phoneNumber, "utf-8");
        String pinyinNameFlag = HttpUtils.doPost(BaseUrl.OA_HOST + API.pinyinName + pinyinName, "utf-8");

        map.put("emailFlag", emailFlag);
        map.put("phoneNumberFlag", phoneNumberFlag);
        map.put("pinyinNameFlag", pinyinNameFlag);

        return map;
    }
}

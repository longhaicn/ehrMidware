package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.API;
import com.api.BaseUrl;
import com.api.entity.OrganizationEntity;
import com.constant.ConstantVar;
import com.dao.OrganizaitonDao;
import com.utils.HttpUtils;
import com.utils.LOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("organizationService")
public class OrganizationService {
    @Autowired
    private OrganizaitonDao organizaitonDao;

    public int syncAllOrganization() {
        List<OrganizationEntity> list = new ArrayList();
        try {
            List<OrganizationEntity> childs = this.organizaitonDao.queryParent(ConstantVar.parentCompany);
            while ((null != childs) && (childs.size() > 0)) {
                for (OrganizationEntity o : childs) {
                    list.add(o);
                }
                childs = buildTree(childs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            String url = API.testConn;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            url = "http://127.0.0.1:8081/archiveAllOrganization";
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
            String url = BaseUrl.HOST + API.organizationInterestSave;
            String res = HttpUtils.doPost(url, json, "utf-8");
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            url = BaseUrl.HOST + API.organizationReset;
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

    public int syncInfluencedOrganization() {
        List<OrganizationEntity> list = new ArrayList<>();
        List<OrganizationEntity> listK = new ArrayList<>();
        List<OrganizationEntity> listD = new ArrayList<>();
        List<OrganizationEntity> listKD = new ArrayList<>();
                String influnencedDate = null;
        try {
            String url = BaseUrl.HOST + API.influnencedOrgDate;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            influnencedDate = j.getString("data");
        } catch (Exception e) {
            LOG.print(e.getMessage());
            return 0;
        }
        try {
            LOG.print("influnencedDate=" + influnencedDate);
            list.clear();
            listD.clear();
            listK.clear();
            listKD.clear();
            list = this.organizaitonDao.selectInfluencedOrganizationList(influnencedDate);
            listD = this.organizaitonDao.selectInfluencedOrganizationListDelete(influnencedDate);
            listK = this.organizaitonDao.selectInfluencedOrganizationListK(influnencedDate);
            listKD = this.organizaitonDao.selectInfluencedOrganizationListKDelete(influnencedDate);
            for (OrganizationEntity entity : listD) {
                System.out.println("listD"+entity.toString());
                list.add(entity);
            }
            for (OrganizationEntity entity : listKD) {
                System.out.println("listKD"+entity.toString());
                listK.add(entity);
            }
            if ((0 == list.size()) && (0 == listK.size())) {
                LOG.print("syncInfluencedOrganization size:0");
                LOG.print("syncInfluencedOrganization abort!");
                return 0;
            }
            LOG.print("syncInfluencedOrganization size:" + (list.size() + listK.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("list", list);
            json.put("listK", listK);
            String url = BaseUrl.HOST + API.organizationInfluencedSave;
            String res = HttpUtils.doPost(url, json, "utf-8");
            JSONObject j = JSON.parseObject(res);
            if (j.getInteger("code").intValue() == 0) {
                return 0;
            }
            LOG.print("syncInfluencedOrganization done!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    public List<OrganizationEntity> buildTree(List<OrganizationEntity> listParent) {
        List<OrganizationEntity> listChild = new ArrayList();
        for (OrganizationEntity entity : listParent) {
            String parent = entity.getOrganizationKey();
            List<OrganizationEntity> childs = this.organizaitonDao.queryChild(parent);
            if ((null != childs) && (childs.size() > 0)) {
                for (OrganizationEntity child : childs) {
                    listChild.add(child);
                }
            }
        }
        return listChild;
    }
}

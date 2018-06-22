package com.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.API;
import com.api.BaseUrl;
import com.api.entity.OrganizationEntity;
import com.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.OrganizaitonDao;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ehrMidware
 * @Package: poly.ehr.service
 * @Author: longhai
 * @CreateDate: 2018/5/15 18:16
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Service("organizationService")
public class OrganizationService {

    @Autowired
    private OrganizaitonDao organizaitonDao;

    /**
     *
     * @param listParent
     * @return
     */
    public List<OrganizationEntity> buildTree(List<OrganizationEntity> listParent){
        List<OrganizationEntity> listChild= new ArrayList<>();
        String parent;
        for (OrganizationEntity entity : listParent) {
            parent = entity.getOrganizationKey();
                List<OrganizationEntity> childs = organizaitonDao.queryChild(parent);
                if (null != childs && childs.size()>0){
                    for (OrganizationEntity child:childs) {
                        listChild.add(child);
                    }
                }
        }
        return listChild;
    }

    /**
     * 全量同步组织架构
     * @return
     */
    public int syncAllOrganization(){
        List<OrganizationEntity> list = new ArrayList<>();
        //1.查询EHR数据库数据
        try{
            List<OrganizationEntity> childs =  organizaitonDao.queryParent("保臻科技公司");
            while (null != childs && childs.size()>0){
                for (OrganizationEntity o: childs) {
                    list.add(o);
                }
                childs = buildTree(childs);
            }
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
                url = BaseUrl.HOST+ API.archiveAllOrganization;
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
            String url = BaseUrl.HOST+ API.organizationInterestSave;
            String res = HttpUtils.doPost(url,json,"utf-8");
            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0){
                return 0;
            }else {
                url = BaseUrl.HOST+ API.organizationReset;
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

    /**
     * 增量同步组织架构
     * @return
     */
    public int syncInfluencedOrganization() {
        List<OrganizationEntity> list,listK=null;
        String influnencedDate=null;
        //1.连接主数据平台 获取上次同步时间 influnenced Date
        try{
            String url = BaseUrl.HOST+ API.influnencedDate;
            String res = HttpUtils.doGet(url);
            JSONObject j = JSON.parseObject(res);
            if(j.getInteger("code") == 0)
                return 0;
            else
                influnencedDate=j.getString("data");

        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //2.查询EHR数据库数据 增量数据 增删改 时间戳 > influnencedDate
        try{
            System.out.println("influnencedDate="+influnencedDate);
            list =organizaitonDao.selectInfluencedOrganizationList(influnencedDate);
            listK =organizaitonDao.selectInfluencedOrganizationListK(influnencedDate);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }

        //3.同步数据
        try{
            JSONObject json = new JSONObject();
            json.put("list",list);
            json.put("listK",listK);
            String url = BaseUrl.HOST+ API.organizationInfluencedSave;
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

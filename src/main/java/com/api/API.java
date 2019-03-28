package com.api;

/**
 * @ProjectName: ehrMidware
 * @Package: com.api
 * @Author: longhai
 * @CreateDate: 2018/5/21 11:58
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class API {

    //删除一条组织架构信息
    public static final String organizationInterestDelete = "organizationInterestDelete";
    //删除所有组织架构信息
    public static final String organizationReset = "organizationReset";
    //查询组织架构信息列表
    public static final String organizationInterestList = "organizationInterestList";
    //查询一条组织架构信息
    public static final String organizationInterestQuery = "organizationInterestQuery";
    //新增一条组织架构信息
    public static final String organizationInterestSave = "organizationInterestSave";
    //更新一条组织架构信息
    public static final String organizationInterestUpdate = "organizationInterestUpdate";
    //过期数据
    public static final String archiveAllOrganization = "archiveAllOrganization";
    //同步组织结构增量信息
    public static final String organizationInfluencedSave = "organizationInfluencedSave";
    //删除一条人员信息
    public static final String stuffInterestDelete = "stuffInterestDelete";
    //删除一条人员信息
    public static final String stuffReset = "stuffReset";
    //查询人员信息列表
    public static final String stuffInterestList = "stuffInterestList";
    //查询一条人员信息
    public static final String stuffInterestQuery = "stuffInterestQuery";
    //新增一条人员信息
    public static final String stuffInterestSave = "stuffInterestSave";
    //更新一条人员信息
    public static final String stuffInterestUpdate = "stuffInterestUpdate";
    //过期数据
    public static final String archiveAllStuff = "archiveAllStuff";
    //同步人员增量信息
    public static final String stuffInfluencedSave = "stuffInfluencedSave";
    //测试
    public static final String testConn = "testConn";
    //获取上次更新时间戳
    public static String influnencedOrgDate = "influnencedOrgDate";
    //获取上次更新时间戳
    public static String influnencedStfDate = "influnencedStfDate";

    public static final String email = "?key=email&value=";
    public static final String pinyinName = "?key=pinyinName&value=";
    public static final String phoneNumber = "?key=phoneNumber&value=";

}

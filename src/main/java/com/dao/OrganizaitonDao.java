package com.dao;

import com.api.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: ehrMidware
 * @Package: poly.ehr.dao
 * @Author: longhai
 * @CreateDate: 2018/5/15 17:39
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface OrganizaitonDao {
    @Select("SELECT " +
            "GUIDKEY as uniqueId,"+
            "codesetid as organizationSetid," +
            "codeitemid as organizationUuid," +
            "codeitemdesc as organization," +
            "parentid as parentUuid," +
            "childid as childrenOuUuid," +
            "corCode as linkingCode," +
            "start_date as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM organization WHERE end_date='9999-12-31';")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> selectOrganizationList();

    @Select("SELECT " +
            "v.unique_id as uniqueId," +
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o.childid as childrenOuUuid," +
            "o.corCode as linkingCode," +
            "v.sDate as createTime," +
            "GETDATE() as ts," +
            "v.flag as archived " +
            "FROM t_org_view v,organization o WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date};")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> selectInfluencedOrganizationList(String date);

    @Select("SELECT " +
            "v.unique_id as uniqueId," +
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o.childid as childrenOuUuid," +
            "o.corCode as linkingCode," +
            "v.sDate as createTime," +
            "GETDATE() as ts," +
            "v.flag as archived " +
            "FROM t_org_view v,organization o WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date };")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> selectInfluencedOrganizationListK(String date);

    @Select("SELECT " +
            "GUIDKEY as uniqueId,"+
            "codesetid as organizationSetid," +
            "codeitemid as organizationUuid," +
            "codeitemdesc as organization," +
            "parentid as parentUuid," +
            "childid as childrenOuUuid," +
            "corCode as linkingCode," +
            "start_date as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM organization WHERE end_date='9999-12-31' AND parentid=#{parentId} AND codeitemid<>'02';")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> queryChild(String parentId);
    @Select("SELECT " +
            "GUIDKEY as uniqueId,"+
            "codesetid as organizationSetid," +
            "codeitemid as organizationUuid," +
            "codeitemdesc as organization," +
            "parentid as parentUuid," +
            "childid as childrenOuUuid," +
            "corCode as linkingCode," +
            "start_date as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM organization WHERE end_date='9999-12-31' AND codeitemdesc=#{organization};")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> queryParent(String organization);
}

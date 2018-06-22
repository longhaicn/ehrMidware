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
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "v.unique_id as organizationKey," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o1.GUIDKEY as parentKey," +
            "o.childid as childrenOuUuid," +
            "o2.GUIDKEY as childrenKey," +
            "o.corCode as linkingCode," +
            "v.sDate as createTime," +
            "GETDATE() as ts," +
            "v.flag as archived " +
            "FROM t_org_view v,organization o " +
            "left join organization o1 on o.parentid=o1.codeitemid " +
            "left join organization o2 on o.childid=o2.codeitemid " +
            "WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date} AND o.codeitemid LIKE '02%' order by v.flag asc;")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organizationKey", property="organizationKey"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="parentKey", property="parentKey"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="childrenKey", property="childrenKey"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> selectInfluencedOrganizationList(String date);

    @Select("SELECT " +
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "v.unique_id as organizationKey," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o1.GUIDKEY as parentKey," +
            "o.childid as childrenOuUuid," +
            "o2.GUIDKEY as childrenKey," +
            "o.corCode as linkingCode," +
            "v.sDate as createTime," +
            "GETDATE() as ts," +
            "v.flag as archived " +
            "FROM t_post_view v,organization o " +
            "left join organization o1 on o.parentid=o1.codeitemid " +
            "left join organization o2 on o.childid=o2.codeitemid " +
            "WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date } AND o.codeitemid LIKE '02%'  order by v.flag asc;")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organizationKey", property="organizationKey"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="parentKey", property="parentKey"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="childrenKey", property="childrenKey"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> selectInfluencedOrganizationListK(String date);

    @Select("SELECT " +
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "o.GUIDKEY as organizationKey," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o1.GUIDKEY as parentKey," +
            "o.childid as childrenOuUuid," +
            "o2.GUIDKEY as childrenKey," +
            "o.corCode as linkingCode," +
            "o.start_date as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM organization o " +
            "left JOIN organization o1 ON o.parentid=o1.codeitemid " +
            "left JOIN organization o2 ON o.childid=o2.codeitemid " +
            "WHERE o.end_date='9999-12-31' AND o1.GUIDKEY=#{parent} AND o.codeitemid<>'02';")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organizationKey", property="organizationKey"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="parentKey", property="parentKey"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="childrenKey", property="childrenKey"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> queryChild(String parent);
    @Select("SELECT " +
            "o.codesetid as organizationSetid," +
            "o.codeitemid as organizationUuid," +
            "o.GUIDKEY as organizationKey," +
            "o.codeitemdesc as organization," +
            "o.parentid as parentUuid," +
            "o1.GUIDKEY as parentKey," +
            "o.childid as childrenOuUuid," +
            "o2.GUIDKEY as childrenKey," +
            "o.corCode as linkingCode," +
            "o.start_date as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM organization o " +
            "left JOIN organization o1 ON o.parentid=o1.codeitemid " +
            "left JOIN organization o2 ON o.childid=o2.codeitemid " +
            "WHERE o.end_date='9999-12-31' AND o.codeitemdesc='保臻科技公司';")
    @Results({
            @Result(column="organizationSetid", property="organizationSetid"),
            @Result(column="organizationUuid", property="organizationUuid"),
            @Result(column="organizationKey", property="organizationKey"),
            @Result(column="organization", property="organization"),
            @Result(column="parentUuid", property="parentUuid"),
            @Result(column="parentKey", property="parentKey"),
            @Result(column="childrenOuUuid", property="childrenOuUuid"),
            @Result(column="childrenKey", property="childrenKey"),
            @Result(column="linkingCode", property="linkingCode"),
            @Result(column="createTime", property="createTime",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="ts", property="ts",javaType = Date.class, jdbcType = JdbcType.DATE),
            @Result(column="archived", property="archived")
    })
    List<OrganizationEntity> queryParent(String organization);
}

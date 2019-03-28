package com.dao;

import com.api.entity.OrganizationEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public abstract interface OrganizaitonDao {
    @Select({"SELECT o.codesetid as organizationSetid,o.codeitemid as organizationUuid,v.unique_id as organizationKey,o.codeitemdesc as organization,o.parentid as parentUuid,o1.GUIDKEY as parentKey,o.childid as childrenOuUuid,o2.GUIDKEY as childrenKey,o.corCode as linkingCode,o.start_date as createTime,v.sDate as ts,v.flag as archived FROM t_org_view v,organization o LEFT JOIN organization o1 on o.parentid=o1.codeitemid LEFT JOIN organization o2 on o.childid=o2.codeitemid WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date} AND o.codeitemid LIKE '02%' AND v.flag<'3' order by v.flag asc;"})
    public abstract List<OrganizationEntity> selectInfluencedOrganizationList(String paramString);

    @Select({"SELECT  v.codesetid as organizationSetid, v.b0110_0 as organizationUuid, v.unique_id as organizationKey, v.codeitemdesc as organization, v.parentid as parentUuid, '' as parentKey, '' as childrenOuUuid, '' as childrenKey, '' as linkingCode, v.sDate as createTime, v.sDate  as ts, v.flag as archived  FROM t_org_view v WHERE  v.sDate > #{date}  AND v.b0110_0 LIKE '02%' AND v.flag='3' order by v.b0110_0 DESC;"})
    public abstract List<OrganizationEntity> selectInfluencedOrganizationListDelete(String paramString);

    @Select({"SELECT o.codesetid as organizationSetid,o.codeitemid as organizationUuid,v.unique_id as organizationKey,o.codeitemdesc as organization,o.parentid as parentUuid,o1.GUIDKEY as parentKey,o.childid as childrenOuUuid,o2.GUIDKEY as childrenKey,o.corCode as linkingCode,o.start_date as createTime,v.sDate as ts,v.flag as archived FROM t_post_view v,organization o LEFT JOIN organization o1 on o.parentid=o1.codeitemid LEFT JOIN organization o2 on o.childid=o2.codeitemid WHERE v.unique_id=o.GUIDKEY AND v.sDate > #{date } AND o.codeitemid LIKE '02%' AND v.flag<'3'  order by v.flag asc;"})
    public abstract List<OrganizationEntity> selectInfluencedOrganizationListK(String paramString);

    @Select({"SELECT  v.codesetid as organizationSetid, v.e01a1_0 as organizationUuid, v.unique_id as organizationKey, v.codeitemdesc as organization, v.parentid as parentUuid, '' as parentKey, '' as childrenOuUuid, '' as childrenKey, '' as linkingCode, v.sDate as createTime, v.sDate as ts, v.flag as archived  FROM t_post_view v WHERE  v.sDate >  #{date}  AND v.e01a1_0 LIKE '02%' AND v.flag='3' order by v.flag asc;"})
    public abstract List<OrganizationEntity> selectInfluencedOrganizationListKDelete(String paramString);

    @Select({"SELECT o.codesetid as organizationSetid,o.codeitemid as organizationUuid,o.GUIDKEY as organizationKey,o.codeitemdesc as organization,o.parentid as parentUuid,o1.GUIDKEY as parentKey,o.childid as childrenOuUuid,o2.GUIDKEY as childrenKey,o.corCode as linkingCode,o.start_date as createTime,GETDATE() as ts,0 as archived FROM organization o LEFT JOIN organization o1 ON o.parentid=o1.codeitemid LEFT JOIN organization o2 ON o.childid=o2.codeitemid WHERE o.end_date='9999-12-31' AND o1.GUIDKEY=#{parent} AND o.codeitemid<>'02';"})
    public abstract List<OrganizationEntity> queryChild(String paramString);

    @Select({"SELECT o.codesetid as organizationSetid,o.codeitemid as organizationUuid,o.GUIDKEY as organizationKey,o.codeitemdesc as organization,o.parentid as parentUuid,'5416564307411909104' as parentKey,o.childid as childrenOuUuid,o2.GUIDKEY as childrenKey,o.corCode as linkingCode,o.start_date as createTime,GETDATE() as ts,0 as archived FROM organization o LEFT JOIN organization o1 ON o.parentid=o1.codeitemid LEFT JOIN organization o2 ON o.childid=o2.codeitemid WHERE o.end_date='9999-12-31' AND o.codeitemdesc='保臻科技公司';"})
    public abstract List<OrganizationEntity> queryParent(String paramString);
}

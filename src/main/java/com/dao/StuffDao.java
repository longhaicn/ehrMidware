package com.dao;

import com.api.entity.StuffEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: ehrMidware
 * @Package: com.dao
 * @Author: longhai
 * @CreateDate: 2018/5/21 15:46
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
public interface StuffDao {
    @Select("SELECT " +
            "u.E0127 as userName," +
            "u.A0101 as displayName," +
            "u.A01DE as namePingyin," +
            "u.A01DF as emails," +
            "u.C0104 as phoneNumber," +
            "u.H01SK as leader," +
            "u.A0177 as idCard," +
            "u.E01A1 as jobId," +
            "o1.GUIDKEY as jobKey," +
            "o1.codeitemdesc as jobName," +
            "u2.C3210 as status," +
            "u.E0122 as organizationUuid," +
            "o2.GUIDKEY as organizationKey," +
            "o2.codeitemdesc as organizationName," +
            "u.CreateTime as createTime," +
            "GETDATE() as ts," +
            "0 as archived " +
            "FROM UsrA01 u " +
            "left JOIN organization o1 ON u.E01A1=o1.codeitemid and o1.codesetid='@K' and o1.end_date='9999-12-31' " +
            "left JOIN organization o2 ON u.E0122=o2.codeitemid and o2.codesetid='UM' and o2.end_date='9999-12-31' " +
            "left JOIN UsrA32 u2 ON u.A0100 = u2.A0100 WHERE u.E01A1 LIKE '02%' order by u.E01A1;" )
    List<StuffEntity> selectStuffList();
    @Select("SELECT" +
            "v.e0127 as userName," +
            "u.A0101 as displayName," +
            "u.A01DE as namePingyin," +
            "u.A01DF as emails," +
            "u.C0104 as phoneNumber," +
            "u.H01SK as leader," +
            "u.A0177 as idCard," +
            "u.E01A1 as jobId," +
            "o1.GUIDKEY as jobKey," +
            "o1.codeitemdesc as jobName," +
            "u2.C3210 as status," +
            "u.E0122 as organizationUuid," +
            "o2.GUIDKEY as organizationKey," +
            "o2.codeitemdesc as organizationName," +
            "u.CreateTime as createTime," +
            "GETDATE() as ts," +
            "v.flag as archived " +
            "FROM t_hr_view v " +
            "left join UsrA01 u on u.A0100 = v.A0100 " +
            "left JOIN organization o1 ON u.E01A1=o1.codeitemid and o1.codesetid='@K' and o1.end_date='9999-12-31' " +
            "left JOIN organization o2 ON u.E0122=o2.codeitemid and o2.codesetid='UM' and o2.end_date='9999-12-31' " +
            "left JOIN UsrA32 u2 ON u.A0100 = u2.A0100 " +
            "WHERE v.E01A1_0 LIKE '02%' AND v.sDate> #{influnencedDate} order by v.E01A1_0;")
    List<StuffEntity> selectInfluencedStuffList(String influnencedDate);
}

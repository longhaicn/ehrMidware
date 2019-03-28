package com.dao;

import com.api.entity.StuffEntity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public abstract interface StuffDao {
    @Select({"SELECT u.E0127 as userName, u.A0101 as displayName, u.A01DE as pinyinName, u.A0107 as gender,u.A01DF as emails, u.C0104 as phoneNumber, u.H01SK as leader, u.A0177 as idCard, o1.GUIDKEY as jobKey, o1.codeitemdesc as jobName, c2.codeitemdesc as jobGrade,u2.C3210 as status, o2.GUIDKEY as organizationKey, o2.codeitemdesc as organizationName, u.CreateTime as createTime, GETDATE() as ts, 0 as archived  FROM UsrA01 u  left JOIN organization o1 ON u.E01A1=o1.codeitemid and o1.codesetid='@K' and o1.end_date='9999-12-31'  left JOIN organization o2 ON u.E0122=o2.codeitemid and o2.codesetid='UM' and o2.end_date='9999-12-31'  left JOIN codeitem c2 on c2.codeitemid = u.H01SX and c2.codesetid='HL' left JOIN (SELECT t.A0100 as 'A0100',t.C3210 as 'C3210' from UsrA32 t right join (select  A0100,max(I9999) as I9999 from UsrA32 a32 group by A0100) t1 on t.A0100 = t1.A0100 and t.I9999 = t1.I9999) u2 ON u2.A0100 = u.A0100 WHERE u.E01A1 LIKE '02%' ORDER BY u.E01A1;"})
    public abstract List<StuffEntity> selectStuffList();


    @Select({"SELECT v.e0127 as userName,v.A0101 as displayName,u.A01DE as pinyinName,u.A0107 as gender,u.A01DF as emails,u.C0104 as phoneNumber,u.H01SK as leader,u.A0177 as idCard,o1.GUIDKEY as jobKey,o1.codeitemdesc as jobName,c2.codeitemdesc as jobGrade,u2.C3210 as status,o2.GUIDKEY as organizationKey,o2.codeitemdesc as organizationName,u.CreateTime as createTime,v.sDate as ts,v.flag as archived FROM t_hr_view v LEFT JOIN UsrA01 u on u.A0100 = v.A0100 LEFT JOIN organization o1 ON u.E01A1=o1.codeitemid AND o1.codesetid='@K' AND o1.end_date='9999-12-31' LEFT JOIN organization o2 ON u.E0122=o2.codeitemid AND o2.codesetid='UM' AND o2.end_date='9999-12-31' LEFT JOIN codeitem c2 on c2.codeitemid = u.H01SX AND c2.codesetid='HL' LEFT JOIN (SELECT t.A0100 AS 'A0100',t.C3210 as 'C3210' from UsrA32 t RIGHT JOIN (select  A0100,max(I9999) AS I9999 FROM UsrA32 a32 GROUP BY A0100) t1 on t.A0100 = t1.A0100 AND t.I9999 = t1.I9999) u2 ON u.A0100 = u2.A0100 WHERE v.E01A1_0 LIKE '02%' AND v.sDate> #{influnencedDate} ORDER BY v.E01A1_0;"})
    public abstract List<StuffEntity> selectInfluencedStuffList(String paramString);
}
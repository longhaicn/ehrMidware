package com.api.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ProjectName: ehrMidware
 * @Package: com.entity
 * @Author: longhai
 * @CreateDate: 2018/5/21 15:47
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Data
public class StuffEntity implements Serializable {
    /**
     * 数据库流水ID
     */
//    private int id;
    /**
     * 数据唯一标识ID
     */
    private String uniqueId;
    /**
     * 平台主账户 工号 E0127
     */
    private String userName;
    /**
     * 用户显示的名称（名字） A0101
     */
    private String displayName;
    /**
     * 名字全拼
     */
    private String pinyinName;
    /**
     * 性别
     */
    private String gender;
    /**
     * 邮箱 C0102
     */
    private String emails;
    /**
     * 手机号 C0104
     */
    private String phoneNumber;

    /**
     * 直接上级领导工号 H01SK
     */
    private String leader;
    /**
     * 身份证号 A0177
     */
    private String idCard;
    /**
     * 岗位ID E01A1
     */
    private String jobId;
    /**
     * 岗位名称 codeitemdesc
     */
    private String jobName;
    /**
     * 职位级别 H01SX
     */
    private String jobGrade;
    /**
     * 是否转正 A32 C3210字段
     */
    private String status;
    /**
     * 所属组织机构的uuid
     */
    private String organizationUuid;
    /**
     * 组织机构名称 E0122（部门）
     */
    private String organizationName;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后操作时间
     */
    private Date ts;
    /**
     * 删除标记
     */
    private int archived;
}

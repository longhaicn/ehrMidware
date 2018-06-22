package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.service.OrganizationService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ProjectName: ehrMidware
 * @Package: poly.ehr.controller
 * @Author: longhai
 * @CreateDate: 2018/5/15 17:39
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
public class OrganizationController {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    @Qualifier("organizationService")
    private OrganizationService organizationService;

    @RequestMapping(value = "/syncAllOrganization")
    public void syncAllOrganization(HttpServletRequest request, HttpServletResponse response) {
     int a = organizationService.syncAllOrganization();
    }

    @RequestMapping(value = "/syncInfluencedOrganization")
    public void syncInfluencedOrganization(HttpServletRequest request, HttpServletResponse response) {

       int a = organizationService.syncInfluencedOrganization();
    }

    @Scheduled(cron = "0 * 5 * * ?")
    public void taskDate(){
        System.out.println("###############################每六小时差异同步组织架构###############################");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        syncInfluencedOrganization(null,null);
    }
}

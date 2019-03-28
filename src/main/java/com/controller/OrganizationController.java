package com.controller;

import com.service.OrganizationService;
import com.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OrganizationController {
    @Autowired
    @Qualifier("organizationService")
    private OrganizationService organizationService;

    @RequestMapping({"/syncAllOrganization"})
    public void syncAllOrganization(HttpServletRequest request, HttpServletResponse response) {
        this.organizationService.syncAllOrganization();
    }

    @RequestMapping({"/syncInfluencedOrganization"})
    public void syncInfluencedOrganization(HttpServletRequest request, HttpServletResponse response) {
        this.organizationService.syncInfluencedOrganization();
    }

    @Scheduled(cron = "0 * * * * ?")
    public void taskDate() {
        System.out.println(DateTimeUtil.getDateStr() + " ######SYNC Influenced Organization Data/M BEFORE######");
        syncInfluencedOrganization(null, null);
    }
}

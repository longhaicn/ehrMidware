package com.controller;

import com.service.StuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: ehrMidware
 * @Package: com.controller
 * @Author: longhai
 * @CreateDate: 2018/5/21 15:44
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2018</p>
 */
@Controller
public class StuffController {
    @Autowired
    @Qualifier("stuffService")
    private StuffService stuffService;

    @RequestMapping(value = "/syncAllStuff")
    public void syncAllStuff(HttpServletRequest request, HttpServletResponse response) {
        stuffService.syncAllStuff();

    }

    @RequestMapping(value = "/syncInfluencedStuff")
    public void syncInfluencedStuff(HttpServletRequest request, HttpServletResponse response) {
        int a= stuffService.syncInfluencedStuff();

    }

    @Scheduled(cron = "0 * 5 * * ?")
    public void taskDate(){
        System.out.println("###############################每六小时差异同步人员库###############################");
        syncInfluencedStuff(null,null);
    }
    @Scheduled(cron = "0 0 0 15 * ?")
    public void taskmonth(){
        System.out.println("###############################每月十五号全量同步人员库###############################");
        syncAllStuff(null,null);
    }


}

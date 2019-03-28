package com.controller;

import com.service.StuffService;
import com.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class StuffController {
    @Autowired
    @Qualifier("stuffService")
    private StuffService stuffService;

    @RequestMapping({"/syncAllStuff"})
    public void syncAllStuff(HttpServletRequest request, HttpServletResponse response) {
        this.stuffService.syncAllStuff();
    }

    @RequestMapping({"/syncInfluencedStuff"})
    public void syncInfluencedStuff(HttpServletRequest request, HttpServletResponse response) {
        this.stuffService.syncInfluencedStuff();
    }

    @RequestMapping({"/checkValues"})
    public String checkValues(String email, String pinyinName, String phoneNumber, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(200);
        response.setContentType("text/html;charset=utf-8");
        HashMap<String, String> map = this.stuffService.checkValues(email, pinyinName, phoneNumber);
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        session.setAttribute("emailFlag", map.get("emailFlag"));
        session.setAttribute("pinyinName", pinyinName);
        session.setAttribute("pinyinNameFlag", map.get("pinyinNameFlag"));
        session.setAttribute("phoneNumber", phoneNumber);
        session.setAttribute("phoneNumberFlag", map.get("phoneNumberFlag"));
        return "result";
    }

    @Scheduled(cron = "20 * * * * ?")
    public void taskDate() {
        System.out.println( DateTimeUtil.getDateStr()+" ######SYNC Influenced Stuff Data /M ######" );
        syncInfluencedStuff(null, null);
    }

}

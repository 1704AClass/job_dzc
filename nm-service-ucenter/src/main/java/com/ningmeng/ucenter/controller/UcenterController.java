package com.ningmeng.ucenter.controller;

import com.ningmeng.api.ucenter.UcenterControllerApi;
import com.ningmeng.framework.domain.ucenter.ext.NmUserExt;
import com.ningmeng.ucenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ASUS on 2020/3/12.
 */
@RestController
@RequestMapping("/ucenter")
public class UcenterController implements UcenterControllerApi{
    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/getuserext")
    public NmUserExt getUserext(@RequestParam("username") String username) {
        NmUserExt nmUserExt = userService.getUserExt(username);
        return nmUserExt;
    }
}

package com.ningmeng.auth.controller;

import com.ningmeng.api.auth.AuthControllerApi;
import com.ningmeng.auth.service.AuthService;
import com.ningmeng.framework.domain.ucenter.ext.AuthToken;
import com.ningmeng.framework.domain.ucenter.request.LoginRequest;
import com.ningmeng.framework.domain.ucenter.response.AuthCode;
import com.ningmeng.framework.domain.ucenter.response.JwtResult;
import com.ningmeng.framework.domain.ucenter.response.LoginResult;
import com.ningmeng.framework.exception.ExceptionCast;
import com.ningmeng.framework.model.response.CommonCode;
import com.ningmeng.framework.model.response.ResponseResult;
import com.ningmeng.framework.utils.CookieUtil;
import com.ningmeng.framework.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ASUS on 2020/3/11.
 */
@RestController
public class AuthController extends BaseController implements AuthControllerApi{
    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;
    @Value("${auth.tokenValiditySeconds}")
    int tokenValiditySeconds;
    @Autowired
    AuthService authService;


    @Override
    @PostMapping("/userlogin")
    public LoginResult login(LoginRequest loginRequest) {
        //校验账号是否输入
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        //校验密码是否输入
        if(StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        AuthToken authToken = authService.login(loginRequest.getUsername(),
                loginRequest.getPassword(), clientId, clientSecret);
        //将令牌写入cookie
        //访问token
        String access_token = authToken.getAccess_token();
        //将访问令牌存储到cookie
        saveCookie(access_token);
        return new LoginResult(CommonCode.SUCCESS,access_token);
    }
    //将令牌保存到cookie
    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        //添加cookie 认证令牌，最后一个参数设置为false，表示允许浏览器获取
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, cookieMaxAge, false);
    }

    @Override
    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        String access_token = getTokenFormCookie();
        System.out.println(access_token+"-------------------------");
        //根据令牌从redis查询jwt
        AuthToken authToken = authService.getUserToken(access_token);
        if(authToken == null){
            return new JwtResult(CommonCode.FAIL,null);
        }
        return new JwtResult(CommonCode.SUCCESS,authToken.getJwt_token());
    }
    //从cookie中读取访问令牌
    private String getTokenFormCookie(){
        Map<String,String> cookieMap = CookieUtil.readCookie(request,"uid");
        String access_token = cookieMap.get("uid");
        return access_token;
    }

    //退出
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //取出身份令牌
        String uid = getTokenFormCookie();
        //删除redis中token
        authService.delToken(uid);
        //清除cookie
        clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    //清除cookie
    private void clearCookie(String token){
        CookieUtil.addCookie(response, cookieDomain, "/", "uid", token, 0, false);
    }
}

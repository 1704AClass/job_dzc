package com.ningmeng.api.auth;

import com.ningmeng.framework.domain.ucenter.request.LoginRequest;
import com.ningmeng.framework.domain.ucenter.response.JwtResult;
import com.ningmeng.framework.domain.ucenter.response.LoginResult;
import com.ningmeng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by ASUS on 2020/3/10.
 */
@Api(value = "用户认证",description = "用户认证接口")
public interface AuthControllerApi {
    @ApiOperation("登录")
    public LoginResult login(LoginRequest loginRequest);
    @ApiOperation("退出")
    public ResponseResult logout();
    @ApiOperation("查询userjwt令牌")
    public JwtResult userjwt();
}

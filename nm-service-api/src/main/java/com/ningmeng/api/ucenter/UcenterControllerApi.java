package com.ningmeng.api.ucenter;

import com.ningmeng.framework.domain.ucenter.ext.NmUserExt;
import io.swagger.annotations.Api;

/**
 * Created by ASUS on 2020/3/12.
 */
@Api(value = "用户中心",description = "用户中心管理")
public interface UcenterControllerApi {
    public NmUserExt getUserext(String username);
}

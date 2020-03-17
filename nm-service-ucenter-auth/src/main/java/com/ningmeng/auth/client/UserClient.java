package com.ningmeng.auth.client;

import com.ningmeng.framework.client.NmServiceList;
import com.ningmeng.framework.domain.ucenter.ext.NmUserExt;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ASUS on 2020/3/12.
 */
@FeignClient(value = NmServiceList.NM_SERVICE_UCENTER)
public interface UserClient {
    @GetMapping("/ucenter/getuserext")
    public NmUserExt getUserext(@RequestParam("username") String username);
}

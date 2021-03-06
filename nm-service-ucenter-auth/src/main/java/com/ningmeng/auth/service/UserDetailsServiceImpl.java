package com.ningmeng.auth.service;

import com.ningmeng.auth.client.UserClient;
import com.ningmeng.framework.domain.ucenter.NmMenu;
import com.ningmeng.framework.domain.ucenter.ext.NmUserExt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    ClientDetailsService clientDetailsService;
    @Autowired
    UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
        if(authentication==null){
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(username);
            if(clientDetails!=null){
                //密码
                String clientSecret = clientDetails.getClientSecret();
                return new User(username,clientSecret,AuthorityUtils.commaSeparatedStringToAuthorityList(""));
            }
        }
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        NmUserExt userext = userClient.getUserext(username);
        if(userext == null){
            //返回NULL表示用户不存在，Spring Security会抛出异常
            return null;
        }
        //取出正确密码（hash值）
        String password = userext.getPassword();
        String user_permission_string = "";
        //指定用户的权限，这里暂时硬编码
        List<String> permissionList = new ArrayList<>();
        /*permissionList.add("course_get_baseinfo");
        permissionList.add("course_find_pic");*/
        //取出用户权限
        List<NmMenu> permissions = userext.getPermissions();
        for (NmMenu nmMenu:permissions){
            permissionList.add(nmMenu.getCode());
        }
        //将权限串中间以逗号分隔
        String permissionString = StringUtils.join(permissionList.toArray(), ",");
        UserJwt userDetails = new UserJwt(username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissionString));
        userDetails.setId(userext.getId());
        userDetails.setUtype(userext.getUtype());//用户类型
        userDetails.setCompanyId(userext.getCompanyId());//所属企业
        userDetails.setName(userext.getName());//用户名称
        userDetails.setUserpic(userext.getUserpic());//用户头像
        userDetails.setCompanyId(userext.getCompanyId());
        return userDetails;
    }
}

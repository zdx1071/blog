package com.gordon.blog.config;

import com.gordon.blog.entity.SysPermission;
import com.gordon.blog.entity.SysRole;
import com.gordon.blog.entity.User;
import com.gordon.blog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by gordon.zhang on 2018/2/10.
 */
public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取session中的用户
        User user=(User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<SysRole> roles = user.getRoleList();
        if(roles.size()>0) {
            for(SysRole role : roles) {
                List<SysPermission> permissions = role.getPermissions();
                if(permissions.size()>0) {
                    for(SysPermission permission : permissions) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);//将权限放入shiro中.
        return info;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String username = utoken.getUsername();
        User user = userService.findByUsername(username);
        //放入shiro.调用CredentialsMatcher检验密码
        return new SimpleAuthenticationInfo(user, user.getPassword(),this.getClass().getName());
    }
}

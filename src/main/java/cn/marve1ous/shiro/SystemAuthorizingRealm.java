package cn.marve1ous.shiro;

import cn.marve1ous.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SystemAuthorizingRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userId = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        List<String> roleList = userService.getUserRole(Integer.parseInt(userId));
        List<String> permList = new ArrayList<>();
        for (String role : roleList) {
            List<String> list = userService.getPermissionByRole(role);
            permList.addAll(list);
        }
        authInfo.addRoles(roleList);
        authInfo.addStringPermissions(permList);
        return authInfo;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userId = (String) token.getPrincipal();
        String password = userService.getUserPwd(Integer.parseInt(userId));
        if (password != null) {
            return new SimpleAuthenticationInfo(userId, password, getName());
        } else
            return null;
    }
}

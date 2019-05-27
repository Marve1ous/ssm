package cn.marve1ous.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Iterator;
import java.util.Set;

public class MyPermissionsAuthorizationFilter extends AuthorizationFilter {

    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = getSubject(request, response);
        String[] perms = (String[]) mappedValue;

        boolean isPermitted = false;
        if (perms != null && perms.length > 0) {
            Set<String> permSet = CollectionUtils.asSet(perms);
            for (String s : permSet)
                if (subject.isPermitted(s))
                    return true;
        }
        else
            isPermitted=true;

        return isPermitted;
    }
}

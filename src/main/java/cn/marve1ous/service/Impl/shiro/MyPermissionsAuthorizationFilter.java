package cn.marve1ous.service.Impl.shiro;

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
            Iterator it = permSet.iterator();
            while (it.hasNext())
                if(subject.isPermitted((String)it.next()))
                    return true;
        }
        else
            isPermitted=true;

        return isPermitted;
    }
}

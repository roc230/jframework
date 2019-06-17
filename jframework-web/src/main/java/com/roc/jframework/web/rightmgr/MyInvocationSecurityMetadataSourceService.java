package com.roc.jframework.web.rightmgr;

import com.roc.jframework.web.rightmgr.dao.ISysPermissionDAO;
import com.roc.jframework.web.rightmgr.entity.SysPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private ISysPermissionDAO sysPermissionDAO;

    //cache
    private HashMap<String, Collection<ConfigAttribute>> map = null;

    /**
     * 把权限信息加载到缓存
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array = null;
        ConfigAttribute attr = null;
        List<SysPermission> permissions = this.sysPermissionDAO.findAll();
        for(SysPermission permission : permissions){
            array = new ArrayList<>();
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，
            // 例如请求方法到ConfigAttribute的集合中去。
            // 此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            attr = new SecurityConfig(permission.getMenu().getName());
            array.add(attr);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getMenu().getUrl(), array);
        }
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //加载权限信息到缓存
        if(map == null || map.size() < 1){
            loadResourceDefine();
        }
        //
        HttpServletRequest request = ((FilterInvocation)o).getHttpRequest();
        AntPathRequestMatcher matcher = null;
        String resUrl = null;
        //遍历权限信息
        //如果匹配到相应信息返回权限信息
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ){
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)){
                return map.get(resUrl);
            }
        }
        //没匹配到权限信息返回null
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

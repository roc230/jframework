package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysRoleDAO;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.entity.SysRole;
import com.roc.jframework.web.rightmgr.service.IAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AuthorizationService implements IAuthorizeService {

    @Autowired
    private ISysAccountDAO sysAccountDAO;
    @Autowired
    private ISysRoleDAO sysRoleDAO;

    @Override
    public void grantRoleToAccount(String roleId, String accountId) {
        if(StringUtils.isAnyNullOrEmpty(roleId, accountId)){
            return ;
        }
        SysAccount account = this.sysAccountDAO.findById(accountId).get();
        if(account == null){
            return ;
        }
        List<SysRole> roles = account.getRoles();
        SysRole role = this.sysRoleDAO.findById(roleId).get();
        roles.add(role);
        account.setRoles(roles);
        this.sysAccountDAO.save(account);
    }

    @Override
    public void deleteRoleFromAccount(String roleId, String accountId) {
        if(StringUtils.isAnyNullOrEmpty(roleId, accountId)){
            return ;
        }
        SysAccount account = this.sysAccountDAO.findById(accountId).get();
        if(account == null){
            return ;
        }
        List<SysRole> roles = account.getRoles();
        for(SysRole r : roles){
            if(r.getId().equals(roleId)){
                roles.remove(r);
                break;
            }
        }
        account.setRoles(roles);
        this.sysAccountDAO.save(account);
    }
}

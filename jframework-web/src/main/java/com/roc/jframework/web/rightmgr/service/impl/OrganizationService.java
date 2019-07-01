package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysOrganizationDAO;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.entity.SysOrganization;
import com.roc.jframework.web.rightmgr.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService implements IOrganizationService {

    @Autowired
    private ISysOrganizationDAO sysOrganizationDAO;
    @Autowired
    private ISysAccountDAO sysAccountDAO;

    @Override
    public SysOrganization add(SysOrganization org, String parentId, String operatorId) {
        if(!StringUtils.isNullOrEmpty(parentId)){
            SysOrganization parent = this.sysOrganizationDAO.getOne(parentId);
            org.setParent(parent);
        }
        if(!StringUtils.isNullOrEmpty(operatorId)){
            SysAccount a = this.sysAccountDAO.getOne(operatorId);

        }
        return this.sysOrganizationDAO.save(org);
    }

    @Override
    public SysOrganization update(SysOrganization org, String parentId, String opertorId) {
        return null;
    }

    @Override
    public List<SysOrganization> getAllEnable() {
        return this.sysOrganizationDAO.getAllEnable();
    }

    @Override
    public List<SysOrganization> getChildren(String orgId) {
        return null;
    }

    @Override
    public SysOrganization get(String id) {
        return null;
    }

    @Override
    public void disable(String id) {

    }

    @Override
    public void delete(String id) {

    }
}

package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.constants.ServiceResult;
import com.roc.jframework.basic.constants.ServiceResultCode;
import com.roc.jframework.basic.utils.ListUtils;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.rightmgr.dao.ISysAccountDAO;
import com.roc.jframework.web.rightmgr.dao.ISysOrganizationDAO;
import com.roc.jframework.web.rightmgr.entity.OrganizationType;
import com.roc.jframework.web.rightmgr.entity.SysAccount;
import com.roc.jframework.web.rightmgr.entity.SysOrganization;
import com.roc.jframework.web.rightmgr.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
        if(org == null){
            return null;
        }
        if(StringUtils.isNullOrEmpty(org.getId())){
            return null;
        }
        SysOrganization old = this.sysOrganizationDAO.getOne(org.getId());
        old.setCode(org.getCode());
        old.setName(org.getName());
        old.setType(OrganizationType.valueOf(org.getType().name()));
        old.setDescription(org.getDescription());
        if(StringUtils.isNullOrEmpty(parentId)){
            old.setParent(null);
        }else{
            SysOrganization parent = this.sysOrganizationDAO.getOne(parentId);
            old.setParent(parent);
        }
        old.setEnable(org.getEnable());
        return this.sysOrganizationDAO.save(old);
    }

    @Override
    public List<SysOrganization> getAllEnable() {
        return this.sysOrganizationDAO.getAllEnable();
    }

    @Override
    public List<SysOrganization> getChildren(String orgId) {
        return this.sysOrganizationDAO.getChildren(orgId);
    }

    @Override
    public SysOrganization get(String id) {
        return null;
    }

    @Override
    public void disable(String id) {

    }

    @Transactional
    @Override
    public ServiceResult delete(String id) {
        if(StringUtils.isNullOrEmpty(id)){
            return ServiceResult.of(ServiceResultCode.ID_IS_EMPTY, null);
        }
        id = id.trim();
        List<SysOrganization> children = this.sysOrganizationDAO.getChildren(id);
        if(ListUtils.isNullOrEmpty(children)){
            this.sysOrganizationDAO.deleteById(id);
            return ServiceResult.of(ServiceResultCode.SUCCESS, null);
        }else{
            return ServiceResult.of(ServiceResultCode.IS_NOT_LEAF, null);
        }
    }

    @Override
    public List<SysOrganization> getByIds(String... ids) {

        return this.sysOrganizationDAO.findAllById(Arrays.asList(ids));
    }
}

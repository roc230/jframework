package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.web.rightmgr.dao.ISysEmployeeDAO;
import com.roc.jframework.web.rightmgr.entity.SysEmployee;
import com.roc.jframework.web.rightmgr.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private ISysEmployeeDAO sysEmployeeDAO;

    @Override
    public SysEmployee add(SysEmployee employee, String organizationId, String accountId) {
        return null;
    }

    @Override
    public SysEmployee update(SysEmployee employee, String organizationId, String accountId) {
        return null;
    }

    @Override
    public SysEmployee disable(String employeeId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public SysEmployee get(String employeeId) {
        Object o = this.sysEmployeeDAO.findById(employeeId).get();
        if(o != null){
            return (SysEmployee)o;
        }
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<SysEmployee> getPageByDepartmentId(String organizationId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return this.sysEmployeeDAO.findAll(pageable);
    }
}

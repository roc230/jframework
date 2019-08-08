package com.roc.jframework.web.rightmgr.service.impl;

import com.roc.jframework.basic.constants.ServiceResult;
import com.roc.jframework.basic.constants.ServiceResultCode;
import com.roc.jframework.basic.utils.StringUtils;
import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.entity.ResultCode;
import com.roc.jframework.web.rightmgr.dao.ISysEmployeeDAO;
import com.roc.jframework.web.rightmgr.dao.ISysOrganizationDAO;
import com.roc.jframework.web.rightmgr.entity.OrganizationType;
import com.roc.jframework.web.rightmgr.entity.SysEmployee;
import com.roc.jframework.web.rightmgr.entity.SysOrganization;
import com.roc.jframework.web.rightmgr.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Transactional
@Service
public class EmployeeService implements IEmployeeService {

    @Autowired
    private ISysEmployeeDAO sysEmployeeDAO;
    @Autowired
    private ISysOrganizationDAO sysOrganizationDAO;

    @Override
    public ExeResult<SysEmployee> add(SysEmployee employee, String[] organizationIds, String accountId) {

        List<SysOrganization> orgs = this.sysOrganizationDAO.findAllById(Arrays.asList(organizationIds));
        for(SysOrganization org : orgs){
            if(org.getType().equals(OrganizationType.COMPANY)){
                employee.setCompany(org);
            }else if(org.getType().equals(OrganizationType.DEPARTMENT)){
                employee.setDepartment(org);
            }else if(org.getType().equals(OrganizationType.STATION)){
                employee.setStation(org);
            }
        }
        SysEmployee emp = this.sysEmployeeDAO.save(employee);
        return ExeResult.of(ResultCode.SUCCESS,null, emp);
    }

    @Override
    public ExeResult<SysEmployee> update(SysEmployee employee, String organizationId, String accountId) {
        return null;
    }

    @Override
    public ExeResult<SysEmployee> disable(String employeeId) {
        return null;
    }

    @Override
    public ExeResult delete(String employeeId) {
        if(StringUtils.isNullOrEmpty(employeeId)){
            return ExeResult.of(ResultCode.ENTITY_EMPTY_ID, "");
        }
        this.sysEmployeeDAO.deleteById(employeeId);
        return ExeResult.of(ResultCode.SUCCESS, "");
    }

    @Transactional(readOnly = true)
    @Override
    public ExeResult<SysEmployee> get(String employeeId) {
        Object o = this.sysEmployeeDAO.findById(employeeId).get();
        if(o != null){
            return ExeResult.of(ResultCode.SUCCESS,"",(SysEmployee)o);
        }
        return ExeResult.of(ResultCode.NO_QUERY_RESULT, "");
    }

    @Transactional(readOnly = true)
    @Override
    public ExeResult<Page<SysEmployee>> getPageByDepartmentId(String departmentId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum-1, pageSize);
        return ExeResult.of(ResultCode.SUCCESS, "", this.sysEmployeeDAO.findAll(pageable));
    }

    @Override
    public ExeResult<Page<SysEmployee>> getPageByCompanyId(String companyId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);

        SysOrganization company = this.sysOrganizationDAO.getOne(companyId);
        SysEmployee e = new SysEmployee();
        e.setCompany(company);
        Example<SysEmployee> example = Example.of(e);
        return ExeResult.of(ResultCode.SUCCESS, null, this.sysEmployeeDAO.findAll(example, pageable));
    }

    @Override
    public ExeResult<Page<SysEmployee>> getPageByStationId(String stationId, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ExeResult<Page<SysEmployee>> getPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum,pageSize);

        SysEmployee employee = new SysEmployee();
        employee.setEnable(true);
        Example<SysEmployee> example = Example.of(employee);

        return ExeResult.of(ResultCode.SUCCESS, null, this.sysEmployeeDAO.findAll(example, pageable));
    }
}

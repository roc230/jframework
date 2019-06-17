package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.web.rightmgr.entity.SysEmployee;
import org.springframework.data.domain.Page;

public interface IEmployeeService {

    SysEmployee add(SysEmployee employee, String organizationId, String accountId);

    SysEmployee update(SysEmployee employee, String organizationId, String accountId);

    SysEmployee disable(String employeeId);

    SysEmployee get(String employeeId);

    /**
     * 分页查询
     * @param organizationId
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<SysEmployee> getPageByDepartmentId(String organizationId, Integer pageNum, Integer pageSize);
}

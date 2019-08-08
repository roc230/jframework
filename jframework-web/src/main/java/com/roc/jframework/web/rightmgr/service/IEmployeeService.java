package com.roc.jframework.web.rightmgr.service;

import com.roc.jframework.basic.constants.ServiceResult;
import com.roc.jframework.web.entity.ExeResult;
import com.roc.jframework.web.rightmgr.entity.SysEmployee;
import org.springframework.data.domain.Page;

public interface IEmployeeService {

    /**
     * 添加员工
     * @param employee
     * @param orgIds 机构ID
     * @param accountId
     * @return
     */
    ExeResult<SysEmployee> add(SysEmployee employee, String[] orgIds, String accountId);

    ExeResult<SysEmployee> update(SysEmployee employee, String organizationId, String accountId);

    ExeResult<SysEmployee> disable(String employeeId);

    ExeResult delete(String employeeId);

    ExeResult<SysEmployee> get(String employeeId);

    /**
     * 分页获取部门的员工
     * @param departmentId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ExeResult<Page<SysEmployee>> getPageByDepartmentId(String departmentId, Integer pageNum, Integer pageSize);

    /**
     * 分页获取公司的员工
     * @param companyId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ExeResult<Page<SysEmployee>> getPageByCompanyId(String companyId, Integer pageNum, Integer pageSize);

    /**
     * 分页获取岗位的员工
     * @param stationId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ExeResult<Page<SysEmployee>> getPageByStationId(String stationId, Integer pageNum, Integer pageSize);

    /**
     * 分页查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    ExeResult<Page<SysEmployee>> getPage(Integer pageNum, Integer pageSize);
}

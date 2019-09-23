package kz.saa.prototype.services;

import kz.saa.prototype.models.pojos.impl.DefaultDepartment;
import kz.saa.prototype.models.pojos.json.DepartmentJson;

import javax.xml.soap.SAAJResult;

public interface DepartmentsService {

    DefaultDepartment showDeptUsers(Long deptId) throws Exception;
    String addDept(DepartmentJson departmentJson);
    String addAnnouncement(Long deptId, String announcement);
}

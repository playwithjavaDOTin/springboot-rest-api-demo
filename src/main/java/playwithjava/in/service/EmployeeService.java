package playwithjava.in.service;

import playwithjava.in.bean.request.EmployeeRequest;
import playwithjava.in.bean.response.EmployeeResponse;
import playwithjava.in.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    public EmployeeResponse storeEmployee(EmployeeRequest request);
    public List<EmployeeResponse> getALlEmployees();
    public EmployeeResponse getEmployeeById(Long empId);
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest);
    public void deleteEmployee(Long empId);
}

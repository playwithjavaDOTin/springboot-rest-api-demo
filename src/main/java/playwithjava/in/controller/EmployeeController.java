package playwithjava.in.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import playwithjava.in.bean.request.EmployeeRequest;
import playwithjava.in.bean.response.EmployeeResponse;
import playwithjava.in.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("addEmployee")
    public ResponseEntity<EmployeeResponse> storeEmployeeData(@RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.storeEmployee(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("getEmployeeById")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long empId) {
        EmployeeResponse response = employeeService.getEmployeeById(empId);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("getAllEmployee")
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        List<EmployeeResponse> response = employeeService.getALlEmployees();
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PutMapping("updateEmployee/empId")
    public ResponseEntity<EmployeeResponse> storeEmployeeData(@RequestBody EmployeeRequest request, @PathVariable Long empId) {
        EmployeeResponse response = employeeService.updateEmployee(request);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("deleteEmployee/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long empId) {
        employeeService.deleteEmployee(empId);
        return ResponseEntity.noContent().build();
    }
}

package playwithjava.in.service.Impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import playwithjava.in.bean.request.EmployeeRequest;
import playwithjava.in.bean.response.EmployeeResponse;
import playwithjava.in.entity.EmployeeEntity;
import playwithjava.in.exception.EmployeeNotFoundException;
import playwithjava.in.mapper.EmployeeMapperService;
import playwithjava.in.repository.EmployeeRepository;
import playwithjava.in.service.EmployeeService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapperService mapperService;

    @Override
    public EmployeeResponse storeEmployee(EmployeeRequest request) {
        EmployeeEntity employeeEntity = mapperService.mapEmployeeReqToEntity(request);
        EmployeeEntity response = employeeRepository.save(employeeEntity);
        return mapperService.mapEmployeeEntityToResponse(response);
    }

    @Override
    public List<EmployeeResponse> getALlEmployees() {
        Function<EmployeeEntity, EmployeeResponse> data = (x) -> mapperService.mapEmployeeEntityToResponse(x);
        List<EmployeeResponse> empResponse = employeeRepository.findAll()
                .stream()
                .map(data)
                .sorted(Comparator.comparing(EmployeeResponse::getName))
                .collect(Collectors.toList());
        return empResponse;
    }

    @Override
    public EmployeeResponse getEmployeeById(Long empId) {
        Function<EmployeeEntity, EmployeeResponse> data = (x) -> mapperService.mapEmployeeEntityToResponse(x);
        return Optional.ofNullable(employeeRepository.findById(empId)).get()
                .map(data)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = employeeRepository.saveAndFlush(mapperService.mapEmployeeReqToEntity(employeeRequest));
        return mapperService.mapEmployeeEntityToResponse(employeeEntity);
    }

    @Override
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }
}

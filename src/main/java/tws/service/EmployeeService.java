package tws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tws.dto.EmployeeDTO;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;


    public EmployeeDTO getEmployWithDesc(String id){
        Employee employee=employeeMapper.getEmployeeByID(id);
        EmployeeDTO employeeDTO=new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setName(employee.getName());
        String desc=String.format("name:%s,age:%s",employee.getName(),employee.getAge());
        employeeDTO.setDesc(desc);
        return employeeDTO;
    }

//    public List<EmployeeDTO> selectAll(String nameLike) {
//        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
//        List<Employee> employeeList=employeeMapper.selectAll(nameLike);
//        employeeDTOList.addAll(employeeList);
//    }
}

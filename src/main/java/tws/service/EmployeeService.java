package tws.service;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tws.dto.EmployeeDTO;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    DozerBeanMapper dozerBeanMapper=new DozerBeanMapper();

//根据id获得
    public EmployeeDTO getEmployWithDesc(String id){
        Employee employee=employeeMapper.getEmployeeByID(id);
        EmployeeDTO employeeDTO=dozerBeanMapper.map(employee,EmployeeDTO.class);
        String desc=String.format("name:%s,age:%s",employee.getName(),employee.getAge());
        employeeDTO.setDesc(desc);
        return employeeDTO;
    }
//获取所有
    public List<EmployeeDTO> selectAll(String nameLike,Integer page,Integer pageSize) {
        List<EmployeeDTO> employeeDTOList=new ArrayList<>();
        List<Employee> employeeList=employeeMapper.selectAll(nameLike,page,pageSize);
        for(Employee employee:employeeList){
            EmployeeDTO employeeDTO=dozerBeanMapper.map(employee,EmployeeDTO.class);
            String desc=String.format("name:%s,age:%s",employee.getName(),employee.getAge());
            employeeDTO.setDesc(desc);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }
//插入
    public void insert(Employee employee) {
        employeeMapper.insert(employee);
    }
//更新
    public void updateEmployeeByID(String employeeID, Employee employee) {
        employeeMapper.updateEmployeeByID(employeeID,employee);
    }
//删除
    public void deleteEmployeeByID(String employeeID) {
        employeeMapper.deleteEmployeeByID(employeeID);
    }
}

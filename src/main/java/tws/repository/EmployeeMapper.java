package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tws.entity.Employee;

import java.util.List;


@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();

    void insert(@Param("employee") Employee employee);

    Employee getEmployeeByID(@Param("employeeID") String employeeID);

    void updateEmployeeByID(@Param("id") String employeeID, @Param("employee") Employee employee);

    void deleteEmployeeByID(@Param("id") String employeeID);
}

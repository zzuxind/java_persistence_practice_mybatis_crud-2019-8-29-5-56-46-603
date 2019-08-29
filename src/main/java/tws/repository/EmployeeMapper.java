package tws.repository;

import org.apache.ibatis.annotations.Mapper;
import tws.entity.Employee;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> selectAll();
}

package tws.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import tws.entity.Employee;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@MybatisTest
public class EmployeeMapperTest {
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @After
    public void teardown(){
        JdbcTestUtils.deleteFromTables(jdbcTemplate,"employee");
    }

    @Test
    public void shouldFetchList(){
        jdbcTemplate.execute("insert into employee values ('0001','name',20)");
        List<Employee> employeeList=employeeMapper.selectAll(null,null,null);
        assertEquals(1,employeeList.size());
    }

    @Test
    public void shouldinsert(){
        Employee employee=new Employee("0002","dingding","21");
       // jdbcTemplate.execute("insert into employee values ('0002','dingding',21)");
        employeeMapper.insert(employee);
        int numbers=JdbcTestUtils.countRowsInTable(jdbcTemplate,"employee");
        assertEquals(1,numbers);
    }
}

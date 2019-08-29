package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tws.entity.Employee;
import tws.repository.EmployeeMapper;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("")
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(employeeMapper.selectAll());
    }
    @GetMapping("{employeeID}")
    public ResponseEntity getEmployeeByID(@PathVariable String employeeID){
        return ResponseEntity.ok(employeeMapper.getEmployeeByID(employeeID));
    }

    @PostMapping()
    public  ResponseEntity insert(@RequestBody Employee employee){
        String id= UUID.randomUUID().toString();
        employee.setId(id);
        employeeMapper.insert(employee);
        return ResponseEntity.created(URI.create("employees/"+id)).build();
    }

    @PutMapping("{employeeID}")
    public ResponseEntity updateEmployeeByID(@PathVariable String employeeID,@RequestBody Employee employee){
        employeeMapper.updateEmployeeByID(employeeID,employee);
        employee.setId(employeeID);
        return ResponseEntity.ok(employee);
    }


}

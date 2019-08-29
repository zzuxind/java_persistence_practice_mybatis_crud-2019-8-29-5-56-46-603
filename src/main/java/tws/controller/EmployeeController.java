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
    public ResponseEntity<List<Employee>> getAll(@RequestParam(value = "nameLike",required = false) String nameLike) {
        return ResponseEntity.ok(employeeMapper.selectAll(nameLike));
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

    @DeleteMapping("{employeeID}")
    public  ResponseEntity deleteEmployeeByID(@PathVariable String employeeID){
        employeeMapper.deleteEmployeeByID(employeeID);
        return  ResponseEntity.ok().build();
    }


}

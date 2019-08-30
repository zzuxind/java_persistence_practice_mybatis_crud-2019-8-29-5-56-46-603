package tws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tws.dto.EmployeeDTO;
import tws.entity.Employee;
import tws.service.EmployeeService;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

//    @Autowired
//    private EmployeeMapper employeeMapper;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<List<EmployeeDTO>> getAll(@RequestParam(value = "nameLike",required = false) String nameLike,
                                                    @RequestParam(value = "page",required = false) Integer page,
                                                    @RequestParam(value = "pageSize",required = false)Integer pageSize)
    {

        return ResponseEntity.ok(employeeService.selectAll(nameLike,page,pageSize));
    }

    @GetMapping("{employeeID}")
    public ResponseEntity getEmployWithDesc(@PathVariable String employeeID){
        return ResponseEntity.ok(employeeService.getEmployWithDesc(employeeID));
    }

    @PostMapping()
    public  ResponseEntity insert(@RequestBody Employee employee){
        String id= UUID.randomUUID().toString();
        employee.setId(id);
        employeeService.insert(employee);
        return ResponseEntity.created(URI.create("employees/"+id)).build();
    }

    @PutMapping("{employeeID}")
    public ResponseEntity updateEmployeeByID(@PathVariable String employeeID,@RequestBody Employee employee){
        employeeService.updateEmployeeByID(employeeID,employee);
        employee.setId(employeeID);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{employeeID}")
    public  ResponseEntity deleteEmployeeByID(@PathVariable String employeeID){
        employeeService.deleteEmployeeByID(employeeID);
        return  ResponseEntity.ok().build();
    }


}

package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    //built create employee REST API
    @PostMapping
    public  Employee createEmployee(@RequestBody Employee employee) {
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    //built get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with Id:"+id));
        return ResponseEntity.ok(employee);
    }

    //built update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employeeDetails ){
      Employee updateEmployee =employeeRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with Id:"+id));

      updateEmployee.setFirstName(employeeDetails.getFirstName());
      updateEmployee.setLastName(employeeDetails.getLastName());
      updateEmployee.setEmailId(employeeDetails.getEmailId());

      employeeRepository.save(updateEmployee);
        return ResponseEntity.ok(updateEmployee);
    }

    //built delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not Exist with Id:"+id));

        employeeRepository.delete(employee);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

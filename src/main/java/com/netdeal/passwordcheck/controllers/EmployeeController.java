package com.netdeal.passwordcheck.controllers;

import com.netdeal.passwordcheck.dtos.EmployeeRecordDto;
import com.netdeal.passwordcheck.models.EmployeeModel;
import com.netdeal.passwordcheck.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public ResponseEntity<Object> getAllEmployees(@RequestParam String filter) {
        if (filter != null && filter.equals("score")) {
            return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findAllByOrderByScoreDesc());
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.findByLevel(0));
        }
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Object> getOneEmployee(@PathVariable(value="id") UUID id) {
        Optional<EmployeeModel> employeeO = employeeRepository.findById(id);
        if (employeeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(employeeO.get());
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> saveEmployee(@RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        if (employeeRecordDto.manager() != null) {
            EmployeeModel manager = employeeRepository.getReferenceById(employeeRecordDto.manager());
            employeeModel.setManager(manager);
            employeeModel.setLevel(manager.getLevel()+1);
        } else {
            employeeModel.setLevel(0);
        }
        int score = calculateScore(employeeRecordDto.password());
        employeeModel.setScore(score);
        employeeModel.setPassword(encodePassword(employeeRecordDto.password()));
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeRepository.save(employeeModel));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateEmployee(@PathVariable(value="id") UUID id,
                                                 @RequestBody @Valid EmployeeRecordDto employeeRecordDto) {
        Optional<EmployeeModel> employeeO = employeeRepository.findById(id);
        if (employeeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        var employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeRecordDto, employeeModel);
        employeeModel.setScore(100);
        return ResponseEntity.status(HttpStatus.OK).body(employeeRepository.save(employeeModel));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable(value="id") UUID id) {
        Optional<EmployeeModel> employeeO = employeeRepository.findById(id);
        if (employeeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Funcionário não encontrado.");
        }
        employeeRepository.delete(employeeO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso!");
    }

    private int calculateScore(String password) {
        // todo
        return 100;
    }

    private String encodePassword(String password) {
        // todo
        return "encripted";
    }
}

package com.qloron.importExcelFile.controller;


import com.qloron.importExcelFile.Service.EmployeeService;
import com.qloron.importExcelFile.entity.Employe;
import com.qloron.importExcelFile.helper.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class Controller {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee/upload")
    public ResponseEntity<?> upload(@RequestParam ("file")MultipartFile file){
        if (ExcelHelper.checkExcelFormat(file)){
            this.employeeService.save(file);

            return ResponseEntity.ok(Map.of("message", "file is uploaded and data is saved to db"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("upload only excel file");
    }

    @GetMapping("/employee")
    public List<Employe> getAllEmployee(){
        return this.employeeService.getAllEmployees();
    }
}

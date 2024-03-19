package com.qloron.importExcelFile.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.qloron.importExcelFile.Service.ExcelService;
import com.qloron.importExcelFile.entity.Employee;
import com.qloron.importExcelFile.helper.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private ExcelService excelService;
    @GetMapping("/getExcelSheet")
    public ResponseEntity<Resource> download() throws IOException
    {
        String filename = "Employee.xlsx";
        ByteArrayInputStream actualData = excelService.getActualData();
        InputStreamResource file = new InputStreamResource(actualData);
        ResponseEntity<Resource> body = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename"+filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
        return body;
    }
    @PostMapping("/x")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file)
    {
        if(Helper.checkExcelFormat(file)){
            excelService.save(file);

            return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));


        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload excel file ");
    }
    @GetMapping("/getAllEmployee")
    public List<Employee> getAllEmployee()
    {
        return excelService.getAllEmployee();
    }
}

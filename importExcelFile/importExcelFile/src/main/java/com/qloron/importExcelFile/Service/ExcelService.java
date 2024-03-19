package com.qloron.importExcelFile.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import com.qloron.importExcelFile.entity.Employee;
import com.qloron.importExcelFile.helper.Helper;
import com.qloron.importExcelFile.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ExcelService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public ByteArrayInputStream getActualData() throws IOException {
        List<Employee> all = employeeRepository.findAll();
        ByteArrayInputStream byteArrayInputStream = Helper.dataToExcel(all);
        return byteArrayInputStream;
    }


    public void save(MultipartFile file)
    {
        try {
            List<Employee> emp = Helper.convertExceltoEmployee(file.getInputStream());
            employeeRepository.saveAll(emp);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public List<Employee> getAllEmployee()
    {
        return employeeRepository.findAll();
    }
}

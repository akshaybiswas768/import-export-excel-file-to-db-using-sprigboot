package com.qloron.importExcelFile.Service;

import com.qloron.importExcelFile.entity.Employe;
import com.qloron.importExcelFile.helper.ExcelHelper;
import com.qloron.importExcelFile.repo.IEmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private IEmployeeRepo employeeRepo;

    public void save (MultipartFile file) {

        try {
            List<Employe> list = ExcelHelper.convertExcelTOList(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<Employe> getAllEmployees(){

        return this.employeeRepo.findAll();

    }

}

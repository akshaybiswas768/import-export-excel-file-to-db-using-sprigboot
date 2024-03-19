package com.qloron.importExcelFile.repo;

import com.qloron.importExcelFile.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepo extends JpaRepository<Employe,Integer> {

}

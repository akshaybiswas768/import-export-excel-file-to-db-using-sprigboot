package com.qloron.importExcelFile.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Employe {

    @Id
    private Integer employeeId;
    private String employeeName;
    private String email;
    private String department;

}

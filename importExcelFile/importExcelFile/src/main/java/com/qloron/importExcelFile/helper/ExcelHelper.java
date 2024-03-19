package com.qloron.importExcelFile.helper;

import com.qloron.importExcelFile.entity.Employe;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    //check file type is excel or not

    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        }else return false;

    }
    // convert excel to list of employee

    public static List<Employe> convertExcelTOList (InputStream is){
        List<Employe> list = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            int rowNumber =0;
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()){
                Row row = iterator.next();

                if (rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cells = row.iterator();

                int cid =0;

                Employe e = new Employe();

                while (cells.hasNext()){
                    Cell cell = cells.next();
                    switch (cid){
                        case 0:
                            e.setEmployeeId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            e.setEmployeeName(cell.getStringCellValue());
                            break;
                        case 2:
                            e.setEmail(cell.getStringCellValue());
                            break;
                        case 3:
                            e.setDepartment(cell.getStringCellValue());
                            break;
                        default:
                            break;

                    }
                    cid++;
                }
                list.add(e);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}

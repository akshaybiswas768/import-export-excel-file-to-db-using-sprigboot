package com.qloron.importExcelFile.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.qloron.importExcelFile.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
public class Helper {


    public static String[] Headers= {"id","name","email","password","role"};


    public static String SHEET_NAME ="Employee_Sheet";


    public static ByteArrayInputStream dataToExcel(List<Employee> list) throws IOException
    {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream out= new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet(SHEET_NAME);
            Row row = sheet.createRow(0);
            for(int i=0 ;i<Headers.length;i++)
            {
                Cell cell =	row.createCell(i);
                cell.setCellValue(Headers[i]);
            }

            int rowIndex = 1;
            for(Employee e:list)
            {
                Row dataRow = sheet.createRow(rowIndex);
                rowIndex++;
                dataRow.createCell(0).setCellValue(e.getEmpId());
                dataRow.createCell(1).setCellValue(e.getName());
                dataRow.createCell(2).setCellValue(e.getEmail());
                dataRow.createCell(3).setCellValue(e.getPassword());
                dataRow.createCell(4).setCellValue(e.getRole());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            workbook.close();
            out.close();
        }
    }
    public static boolean checkExcelFormat(MultipartFile file)
    {
        String contentType = file.getContentType();

        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }
    public static List<Employee>  convertExceltoEmployee(InputStream stream) throws Exception
    {
        List<Employee> list = new ArrayList<Employee>();
        XSSFWorkbook workbook = new XSSFWorkbook(stream);
        XSSFSheet sheet =workbook.getSheet("Sheet1");
        try {
            int rownumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while(iterator.hasNext())
            {
                Row row = iterator.next();
                if(rownumber==0)
                {
                    rownumber++;
                    continue;
                }
                Iterator<Cell> cells= row.iterator();
                int cid = 0;
                Employee e = new Employee();
                while(cells.hasNext())
                {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            e.setEmpId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            e.setName(cell.getStringCellValue());
                            System.out.println(cell.getStringCellValue());
                            break;
                        case 2:
                            e.setEmail(cell.getStringCellValue());
                            break;
                        case 3:
                            e.setPassword(cell.getStringCellValue());
                            break;
                        case 4:
                            e.setRole(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                list.add(e);
            }
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            workbook.close();
        }
        return list;
    }
}
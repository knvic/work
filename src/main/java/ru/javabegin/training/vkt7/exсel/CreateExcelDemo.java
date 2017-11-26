package ru.javabegin.training.vkt7.exсel;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;


    public class CreateExcelDemo {

        private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
            HSSFFont font = workbook.createFont();
            font.setItalic(true);
            //setBold(true);
            HSSFCellStyle style = workbook.createCellStyle();
            style.setFont(font);
            return style;
        }

        public static void main(String[] args) throws IOException {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Employees sheet");

            List<Employee> list = EmployeeDAO.listEmployees();

            int rownum = 0;
            Cell cell;
            Row row;
            //
            HSSFCellStyle style = createStyleForTitle(workbook);

            row = sheet.createRow(rownum);

            // EmpNo
            cell = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell.setCellValue("EmpNo");
            cell.setCellStyle(style);
            // EmpName
            cell = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell.setCellValue("EmpNo");
            cell.setCellStyle(style);
            // Salary
            cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Salary");
            cell.setCellStyle(style);
            // Grade
            cell = row.createCell(3, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Grade");
            cell.setCellStyle(style);
            // Bonus
            cell = row.createCell(4, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Bonus");
            cell.setCellStyle(style);

            // Data
            for (Employee emp : list) {
                rownum++;
                row = sheet.createRow(rownum);

                // EmpNo (A)
                cell = row.createCell(0,Cell.CELL_TYPE_STRING);
                cell.setCellValue(emp.getEmpNo());
                // EmpName (B)
                cell = row.createCell(1,Cell.CELL_TYPE_STRING);
                cell.setCellValue(emp.getEmpName());
                // Salary (C)
                cell = row.createCell(2, Cell.CELL_TYPE_STRING);
                cell.setCellValue(emp.getSalary());
                // Grade (D)
                cell = row.createCell(3, Cell.CELL_TYPE_STRING);
                cell.setCellValue(emp.getGrade());
                // Bonus (E)
                String formula = "0.1*C" + (rownum + 1) + "*D" + (rownum + 1);
                cell = row.createCell(4, Cell.CELL_TYPE_FORMULA);
                cell.setCellFormula(formula);
            }
            File file = new File("C:/demo/employee.xls");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());

        }



}

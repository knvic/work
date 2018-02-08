package ru.javabegin.training.vkt7.exсel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ConvertExcelTest {


    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setItalic(true);
        //setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setBorderLeft(CellStyle.BORDER_DOUBLE);
        style.getBorderLeft();
        return style;
    }

    @Test
    public void excel() throws Exception {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("куку");


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Data");
        cell.setCellStyle(style);
        int i = 1;
        List<String> l=new ArrayList<>();
        l.add("qwe");
        l.add("wer");
        l.add("fgfh");
        System.out.println("размер= "+ l.size());



        for (String col : l) {
            // EmpNo
            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col.toString());
            cell.setCellStyle(style);
            i++;
        }

/*
        // Data
        for (DataObject emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);
            cell.setCellValue(emp.getStaticval2());
            for(String c:column){
                cell = row.createCell(1,Cell.CELL_TYPE_STRING);
                cell.setCellValue(emp.getEmpName());
            }
        }

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
        }*/
        File file = new File("C:/demo/employee1.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }

    }


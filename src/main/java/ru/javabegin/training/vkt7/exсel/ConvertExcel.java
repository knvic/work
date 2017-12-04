package ru.javabegin.training.vkt7.exсel;


import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javabegin.training.vkt7.auxiliary_programs.AuxiliaryService;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.reports.DataObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class ConvertExcel {


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




    public void excel_current(Customer customer, List<DataObject> list, DataObject sum, DataObject average,List<DataObject> total_list, List<String> column,List<String> col_sum_average,List<String> list_calc_total) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(customer.getFirstName());


        int rownum = 0;
        Cell cell;
        Row row;
        //
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);

        cell.setCellValue("Потребитель:");
        cell.setCellStyle(style);


        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellValue(customer.getFirstName());
        cell.setCellStyle(style);
        rownum++;

        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);

        cell.setCellValue("Адрес :");
        cell.setCellStyle(style);


        cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        cell.setCellValue(customer.getAddress());
        cell.setCellStyle(style);

        rownum++;
        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Data");
        cell.setCellStyle(style);
        int i = 1;
        for (String col : column) {
            // EmpNo
            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col);
            cell.setCellStyle(style);
            i++;
        }

        // Data
        for (DataObject emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);
            cell.setCellValue(emp.getStaticval2());
            i=1;
            for(String c:column){
                cell = row.createCell(i,Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(emp.getOptionalValues().get(c).getValue().doubleValue());
                i++;
            }
        }

        ///создаем вывод суммы и средних значений



        rownum++;
        rownum++;


        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Data");
        cell.setCellStyle(style);
        i = 1;
        for (String col : col_sum_average) {
            // EmpNo
            if(col.contains("HC")){
                continue;
            }

            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col);
            cell.setCellStyle(style);
            i++;
        }



        List<DataObject> list_temp=new ArrayList<>();
        list_temp.add(0,sum);
        list_temp.add(1,average);
        for (DataObject emp1 : list_temp) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);
            cell.setCellValue(emp1.getStaticval2());
            i=1;
            for(String c:col_sum_average){

                cell = row.createCell(i,Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(emp1.getOptionalValues().get(c).getValue().doubleValue());
                i++;
            }
        }


        //выводим итоговые

        rownum++;
        rownum++;


        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Data");
        cell.setCellStyle(style);
        i = 1;
        for (String col : list_calc_total) {
            // EmpNo


            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col);
            cell.setCellStyle(style);
            i++;
        }

        total_list.get(1).setStaticval2(list.get(list.size()-1).getStaticval2());
        total_list.get(2).setStaticval2("ИТОГО:");


        for (DataObject emp1 : total_list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);



            cell.setCellValue(emp1.getStaticval2());
            i=1;
            for(String c:list_calc_total){

                cell = row.createCell(i,Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(emp1.getOptionalValues().get(c).getValue().doubleValue());
                i++;
            }
        }

        LocalDateTime dateTime=LocalDateTime.now();

        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm")));

        File file = new File("C:/demo/"+customer.getFirstName()+"_"+dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }



    public void excel(Customer customer, List<DataObject> list, List<String> column) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet(customer.getFirstName());


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
        for (String col : column) {
            // EmpNo
            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col);
            cell.setCellStyle(style);
            i++;
        }

        // Data
        for (DataObject emp : list) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);
            cell.setCellValue(emp.getStaticval2());
            i=1;
            for(String c:column){
                cell = row.createCell(i,Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(emp.getOptionalValues().get(c).getValue().doubleValue());
                i++;
            }
        }



        File file = new File("C:/demo/"+customer.getFirstName()+".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }



}





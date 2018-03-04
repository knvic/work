package ru.javabegin.training.tv7.excel;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import ru.javabegin.training.tv7.entity.Operationtv7;
import ru.javabegin.training.tv7.entity.Operationtv7T;
import ru.javabegin.training.vkt7.entities.Customer;
import ru.javabegin.training.vkt7.entities.Operation;
import ru.javabegin.training.vkt7.reports.DataObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelArchive {



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


    //public void archiveExcel_tv1 (Customer customer, List<Object> objectsList) throws IOException {
    public void archiveExcel_tv1 (Customer customer, List<String>id_coil_archive, List<DataObjectTv7> atchive, List<String>id_coil_total,List<DataObjectTv7> total, String tv) throws IOException {

       /* List<String>id_coil_archive= (List<String>) objectsList.get(0);
        List<DataObjectTv7> atchive =(List<DataObjectTv7>)objectsList.get(1);
        List<String>id_coil_total= (List<String>) objectsList.get(2);
        List<DataObjectTv7> total =(List<DataObjectTv7>)objectsList.get(3);*/

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

        cell.setCellValue("Заводской номер :");
        cell.setCellStyle(style);


       /* cell = row.createCell(2, Cell.CELL_TYPE_STRING);
        *//*cell.setCellValue(String.valueOf(customer.getOperationSet().stream()
        .filter(p->p.getIdentificator()!=null)
        .findFirst(t->)));*//*
        Operation o= customer.getOperationSet().stream().max((p1, p2)->p1.getChronological().compareTo(p2.getChronological())).get();



        cell.setCellValue(o.getIdentificator());
        cell.setCellStyle(style);*/

        rownum++;
        rownum++;
        row = sheet.createRow(rownum);
        cell = row.createCell(0, Cell.CELL_TYPE_STRING);
        cell.setCellValue("Data");
        cell.setCellStyle(style);
        int i = 1;
        for (String col : id_coil_archive) {
            // EmpNo
            cell = row.createCell(i, Cell.CELL_TYPE_STRING);

            cell.setCellValue(col);
            cell.setCellStyle(style);
            i++;
        }

        // Data
        for (DataObjectTv7 emp : atchive) {
            rownum++;
            row = sheet.createRow(rownum);

            // EmpNo (A)
            cell = row.createCell(0,Cell.CELL_TYPE_STRING);
            cell.setCellValue(emp.getDataString());
            i=1;
            for(String c:id_coil_archive){
                cell = row.createCell(i,Cell.CELL_TYPE_NUMERIC);
                try {

                    cell.setCellValue(emp.getOptionalValues().get(c).getValue());
                }catch (Exception e){

                    cell.setCellValue(0);
                }

                i++;
            }
        }

        ///создаем вывод суммы и средних значений



        rownum++;
        rownum++;




        LocalDateTime dateTime=LocalDateTime.now();

        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm")));

        //File file = new File("C:/TV7/"+customer.getFirstName()+"_"+dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+".xls");
        String CustomerName=customer.getFirstName().replace("\"","");
        File file = new File("C:/demo/TV7/"+CustomerName+"_"+tv+"_"+dateTime.format(DateTimeFormatter.ofPattern("d_MM_uuuu_HH_mm"))+".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());

    }




}

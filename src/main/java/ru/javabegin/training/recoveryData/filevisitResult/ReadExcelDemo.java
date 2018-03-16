package ru.javabegin.training.recoveryData.filevisitResult;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class ReadExcelDemo {

    public static void main(String[] args) throws IOException {


       /* InputStream in = new FileInputStream("C:/demo/vkt/test.xls");
        HSSFWorkbook wb = new HSSFWorkbook(in);

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> it = sheet.iterator();
        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                switch (cellType) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "=");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print("[" + cell.getNumericCellValue() + "]");
                        break;

                    case Cell.CELL_TYPE_FORMULA:
                        System.out.print("[" + cell.getNumericCellValue() + "]");
                        break;
                    default:
                        System.out.print("|");
                        break;
                }
            }
            System.out.println();
        }*/


        // Read XSL file ДолинаНарзанов_тп_1__05_12_2017.xls


        String str="ДолинаНарзанов.xls";
        //Translit tr=new Translit();
        str= Translit.cyr2lat(str);
        System.out.println("str "+str);

        str="test.xls";
        FileInputStream inputStream = new FileInputStream(new File("C:/demo/vkt/"+str));


        //FileInputStream inputStream = new FileInputStream(new File("C:/demo/vkt/test.xls"));
        //FileInputStream inputStream = new FileInputStream(new File("C:/demo/vkt/ДолинаНарзанов_тп_1__05_12_2017.xls"));
       // File file = new File("C:/demo/vkt/ДолинаНарзанов_тп_1__05_12_2017.xls");

        // Get the workbook instance for XLS file
        //HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        //FileInputStream inputStream = new FileInputStream(file);
        //HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        HSSFSheet sheet1 = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet1.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Change to getCellType() if using POI 4.x
                //CellType cellType = cell.getCellTypeEnum();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getRichStringCellValue().getString());
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_BLANK:
                        System.out.print("");
                        System.out.print("\t");
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        // Formula
                        System.out.print(cell.getCellFormula());
                        System.out.print("\t");

                        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                        // Print out value evaluated by formula
                        System.out.print(evaluator.evaluate(cell).getNumberValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue());
                        System.out.print("\t");
                        break;

                    case Cell.CELL_TYPE_ERROR:
                        System.out.print("!");
                        System.out.print("\t");
                        break;
                }

            }
            System.out.println("");
        }
    }

}
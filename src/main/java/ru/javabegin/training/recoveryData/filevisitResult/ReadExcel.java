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

public class ReadExcel {

    public  void read(String name) throws IOException {




        // Read XSL file ДолинаНарзанов_тп_1__05_12_2017.xls


        String str="ДолинаНарзанов.xls";
        //Translit tr=new Translit();
        str= Translit.cyr2lat(str);
        System.out.println("str "+str);

        str="test.xls";


        FileInputStream inputStream = new FileInputStream(new File(name));



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
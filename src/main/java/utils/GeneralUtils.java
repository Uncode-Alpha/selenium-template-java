package utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class GeneralUtils {
    //Method that opens an excel file using its path, then writes a value passed as parameter to the first column and first row of the file
    //TODO: Test this method & replicate thread locking for writing on the file in parallel
    public static void writeToExcelFile(String filePath, String value) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(value);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            System.out.println("Error writing to Excel file: " + e.getMessage());
        }
    }
}

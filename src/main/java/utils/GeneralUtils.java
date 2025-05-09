package utils;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class GeneralUtils {
    //Method that opens an excel file using its path, then writes a value passed as parameter to the first column and first row of the file
    //TODO: Test this method & replicate thread locking for writing on the file in parallel
    public static void writeToExcelFile(String filePath, String value,int range) {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.createRow(range);
            Cell cell = row.createCell(0);
            cell.setCellValue(value);
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            System.out.println("Error writing to Excel file: " + e.getMessage());
        }
    }
    public static void threadSafeExcelWrite(String filePath, String value,String range){
        ReentrantLock lock = new ReentrantLock();
        int len=-1;
        if(range.contains("-")) {
            String[] ran = String.format(range).split("-");
            len = Integer.parseInt(ran[1])-Integer.parseInt(ran[0])+1;
            for(int i=1;i<len;i++){
                threadLockExcel(filePath, value,i);
            }
        }
    }
    public static void threadLockExcel(String filePath, String value,int range){
        ReentrantLock lock = new ReentrantLock();
        try{
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                writeToExcelFile(filePath, value, range);
            }
        }catch(InterruptedException e){
            System.out.println("Unable to acquire lock,thread interrupted: " + e.getMessage());
        }finally{
            lock.unlock();
        }
    }
}

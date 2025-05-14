package factory.dataprovider;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelRead {
    public static List<? extends Object> insertToHashTable(Workbook workbook, String className, String range){
        Sheet sheet = workbook.getSheet(className);
        List<Object> objectHash = mapDataToPojo();
        return objectHash;
    }
    public static List<Object> mapDataToPojo(Workbook workbook, Sheet sheet, String range){
        // Implement the logic to map data to POJO
        String[] split = range.split("-");
        int startingIndex = Integer.parseInt(split[0]);
        int endingIndex = Integer.parseInt(split[1]);
        List<Object> rowData = new ArrayList<>(endingIndex);
        for(int rowNum = startingIndex; rownNum <= endingIndex; rowNum++){
            rowData.add(mapDataToPojo(workbook,sheet,rowNum));
        }
        return rowData;
    }
    private static HashMap<Object,Object> mapDataToPojo(Workbook workbook, Sheet sheet, int rowNum){
        HashMap<Object,Object> pojoMap = new HashMap<>();
        int columns = sheet.getRow(0).getPhysicalNumberOfCells();
        for(int noOfColumns = 1; noOfColumns < columns; noOfColumns){
            final Object objectKey = returnCellValue(sheet.getRow(0).getCell(noOfColumns));
        }
        // Implement the logic to map data to POJO
        return rowData;
    }
}

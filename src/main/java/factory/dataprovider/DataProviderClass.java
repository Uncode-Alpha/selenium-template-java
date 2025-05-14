package factory.dataprovider;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.lang.reflect.Method;

public class DataProviderClass {
    @DataProvider(name = "UniversalRowProvider", parallel = true)
    public Object[][] universalRowProvider(Method m) throws Exception {
        String className = "something";
            String sheetName= m.getAnnotation(DataProviderClass.class).sheetName();
            String fileName=m.getAnnotation(DataProviderClass.class).fileName();
            String range=m.getAnnotation(DataProviderClass.class).range();
            TYPE_OF_REQUEST type_of_request=m.getAnnotation(DataProviderClass.class).requestType();
            Object[][] rows = null;
            try{
                ExcelRead excelReader = new ExcelRead();
                String excelAbsPath = System.getProperty("user.dir")+"/"+fileName;
                Workbook workbook = WorkbookFactory.create(new File(excelAbsPath));
                int rowCount = workbook.getSheet(sheetName).getLastRowNum();
                range = (range == null || range.isEmpty()) ? (range = "1-"+rowCount) :
                        (range = (range.contains("-"))? range : (range = range+"-"+range));
                List<? extends Object> objects = ExcelRead.insertToHashTable(workbook, className,range);
                rows = new Object[objects.size()][]
            }catch(Exception e){

            }
            return rows;
    }
}

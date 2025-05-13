package testscriptexample;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.GeneralUtils;

public class threadSafeExcelWriteTest {
    private static final String FILE_PATH = "src/test/resources/testdata/ThreadSafeTest.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String RANGE = "5-10";

    //TODO: Fix dataprovider implementation
    @DataProvider(name = "excelDataProvider", parallel = true,range = RANGE)
    @Test( dataProvider = "excelDataProvider",dataProviderClass = GeneralUtils.class,sheetName = SHEET_NAME)
    public void threadSafeTest(){
        GeneralUtils.threadSafeExcelWrite();
    }
}

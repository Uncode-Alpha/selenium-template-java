package testscriptexample;

import factory.dataprovider.DataProviderFactory;
import factory.dataprovider.TYPE_OF_REQUEST;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.GeneralUtils;

public class threadSafeExcelWriteTest {
    private static final String FILE_PATH = "src/test/resources/testdata/ThreadSafeTest.xlsx";
    private static final String SHEET_NAME = "Sheet1";
    private static final String RANGE = "5-10";

    //TODO: Fix dataprovider implementation
    @DataProviderFactory(typeOfRequest = TYPE_OF_REQUEST.POJO,fileName = FILE_PATH, range = RANGE)
    @Test(testName = "DataProvider",dataProvider="UniversalRowProvider", dataProviderClass = DataProviderClass.class)
    public void threadSafeTest(){
        GeneralUtils.threadSafeExcelWrite();
    }
}

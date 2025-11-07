package utils;

import com.qa.util.ExcelReader;

import java.io.IOException;
import java.util.Map;

public class ExcelUtils {

    public Map<String, String> fetchExcelRowData(String sheetName, int rowNumber) throws IOException {
        String[] excelNameParts = sheetName.split("-", 2);

        if (excelNameParts.length < 2) {
            throw new IllegalArgumentException("Invalid sheet format. Expected format: <ExcelFileName>-<SheetName>");
        }

        String excelWBName = excelNameParts[0].trim();
        String excelSheetName = excelNameParts[1].trim();

        ExcelReader excelReader = new ExcelReader("src/test/resources/testdata/" + excelWBName + ".xlsx");
        return excelReader.getRowData(excelSheetName, rowNumber);
    }
}

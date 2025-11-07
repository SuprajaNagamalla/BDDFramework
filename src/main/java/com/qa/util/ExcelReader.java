package com.qa.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;

    public ExcelReader(String excelFilePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);
        workbook = new XSSFWorkbook(fileInputStream);
        sheet = workbook.getSheet(sheetName);
    }

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }



    public String getCellData(int rowNumber, int cellNumber) {

        if (sheet == null) return null;
        Row row = sheet.getRow(rowNumber);
        if (row == null) return null;
        Cell cell = row.getCell(cellNumber);
        return (cell != null) ? cell.toString() : null;
    }

    public Map<String, String> getRowData(String sheetName, int rowNum) throws IOException {

        FileInputStream file = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetName);

        Row headerRow = sheet.getRow(0);
        Row dataRow = sheet.getRow(rowNum + 1);  // +1 to skip header

        Map<String, String> rowData = new HashMap<>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
            String key = headerRow.getCell(i).getStringCellValue();
            String value = dataRow.getCell(i).getStringCellValue();
            rowData.put(key, value);
        }

        workbook.close();
        file.close();
        return rowData;
    }

    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    public static Map<String, Map<String, String>> readExcelData(String filePath, String sheetName) {
        Map<String, Map<String, String>> dataList = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

            Row headerRow = sheet.getRow(0); // First row as header
            int totalColumns = headerRow.getLastCellNum(); // Includes blank columns

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                int actualDataRow = i-1;
                String rowNo = actualDataRow+"";
                if (currentRow == null || isRowEmpty(currentRow)) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < totalColumns; j++) {
                    String key = getCellValue(headerRow.getCell(j));
                    String value = getCellValue(currentRow.getCell(j));
                    rowData.put(key, value);
                }
                //dataList.add(rowData);
                dataList.put(rowNo, rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataList;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING: return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN: return String.valueOf(cell.getBooleanCellValue());
            case FORMULA: return cell.getCellFormula();
            case BLANK: return "";
            default: return "";
        }
    }

    private static boolean isRowEmpty(Row row) {
        for (int i = 0; i < row.getLastCellNum(); i++) {
            Cell cell = row.getCell(i);
            if (cell != null && cell.getCellType() != CellType.BLANK && !getCellValue(cell).isEmpty()) {
                return false;
            }
        }
        return true;
    }



}


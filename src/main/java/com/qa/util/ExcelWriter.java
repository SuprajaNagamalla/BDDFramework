package com.qa.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class ExcelWriter {

    public static void writeProductListToExcel(Set<String> productList, String filePath, String SheetName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(SheetName.trim());

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Product ID");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("RACStoreNo");


        // Write product IDs to rows
        int rowNum = 1;
        for (String productId : productList) {
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(productId);

            //creating second cell
            Cell cell2 = row.createCell(1);
            cell2.setCellValue("02271");
        }

        // Auto-size column
        sheet.autoSizeColumn(0);

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file written successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeProductListToExcelUsingMap(Map<String, ArrayList<String>> productList, String filePath, String SheetName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(SheetName.trim());

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Product ID");

        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("RACStoreNo");

        Cell headerCell3 = headerRow.createCell(2);
        headerCell3.setCellValue("Name");

        Cell headerCell4 = headerRow.createCell(3);
        headerCell4.setCellValue("RACInventoryType");


        // Write product IDs to rows
        int rowNum = 1;
        // Read and print the map
        for (Map.Entry<String, ArrayList<String>> entry : productList.entrySet()) {
            String key = entry.getKey();
            System.out.println("Inventory ID: " + key);
            ArrayList<String> values = entry.getValue();
            Row row = sheet.createRow(rowNum++);
            Cell cell = row.createCell(0);
            cell.setCellValue(key);

//            //creating second cell
            Cell cell2 = row.createCell(1);
            cell2.setCellValue("02271");


            System.out.println("value: ");
            Cell cell3 = row.createCell(2);
            cell3.setCellValue(values.get(0));

            Cell cell4 = row.createCell(3);
            cell4.setCellValue(values.get(1));

            for (String value : values) {
                System.out.println("- " + value);
            }
            System.out.println();
        }

        // Auto-size column
        sheet.autoSizeColumn(0);

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
            System.out.println("Excel file written successfully: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


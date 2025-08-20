/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.demo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author PRAKRITI
 */
public class ExcelDataLoader {

    
    public static List<Map<String, String>> loadData(String fileName, String sheetName,String TestCaseName) {
        List<Map<String, String>> dataList = new ArrayList<>();

        // Load file from classpath
         try (InputStream inputStream = ExcelDataLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new RuntimeException("File not found: " + fileName);
            }

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            // First row is header
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                // Check if TestCase column matches
                String currentTestCase = currentRow.getCell(0).getStringCellValue();
                if (!currentTestCase.equalsIgnoreCase(TestCaseName)) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < colCount; j++) {
                    Cell cell = currentRow.getCell(j);
                    String cellValue = (cell == null) ? "" : cell.toString();
                    String header = headerRow.getCell(j).toString();
                    rowData.put(header, cellValue);
                }
                dataList.add(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel file", e);
        }
        return dataList;
    }



    public static void main(String[] args) {
        // Example usage
        List<Map<String, String>> data = loadData("Product_data.xlsx", "Product","Fish");
        for (Map<String, String> row : data) {
            System.out.println(row);
        }
    }
    
}

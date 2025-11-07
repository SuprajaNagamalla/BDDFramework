package com.qa.util;


import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportHelper {

    private static final Logger Log = LoggerHelper.getLogger();

    public static void renameReportFilesWithTimeStamp() {
        try {
            // Rename files with timestamp
            renameFileWithTimestamp("test-output/ExtentReport/", "SparkReport.html");
            renameFileWithTimestamp("test-output/PdfReport/", "ExtentPdf.pdf");
            renameFileWithTimestamp("test-output/CucumberReport/", "cucumber-report.html");

        } catch (Exception e) {
            Log.error("Error occurred while renaming reports", e);
        }

    }

    private static void renameFileWithTimestamp(String folderPath, String oldFileName) {
        File oldFile = new File(folderPath, oldFileName);

        if (!oldFile.exists()) {
            Log.warn("Report file not found: {}", oldFile.getAbsolutePath());
            return;
        }

        // Generate timestamp (yyyy-MM-dd_HH-mm-ss)
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

        // Replace file extension dynamically
        String newFileName = oldFileName.replaceFirst("(\\.\\w+)$", "_" + timestamp + "$1");
        File newFile = new File(folderPath, newFileName);

        // Attempt to rename the file
        if (oldFile.renameTo(newFile)) {
            Log.info("Report renamed: {}", newFile.getAbsolutePath());
        } else {
            Log.error("Failed to rename: {}", oldFile.getAbsolutePath());
        }
    }

    public static String addSpace() {
       //Add leading space
       return Constants.SPACE_SUB_STEP_EXTENT_REPORT;
    }



}

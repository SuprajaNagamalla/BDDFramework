package com.qa.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggerHelper {
    private static Logger logger;
    private static final String TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());

    static {
        String logFilePath = "logs/test-log-" + TIMESTAMP + ".log";
        System.setProperty("logFilename", logFilePath);
        Configurator.initialize(null, "src/test/resources/log4j2.xml");
        logger = LogManager.getLogger(LoggerHelper.class);
    }

    public static Logger getLogger() {
        return logger;
    }
}

package com.legends.edumia.utils;


import com.legends.edumia.Edumia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(Edumia.MOD_ID);
    private static LoggerUtil singleInstance = null;

    private static synchronized LoggerUtil updateInstance() {
        if (singleInstance == null) singleInstance = new LoggerUtil();
        return singleInstance;
    }

    public LoggerUtil() {
    }

    public static void logDebugMsg(String msg) {
        if(Edumia.IS_DEBUG)
            updateInstance();
            LOGGER.info(msg);
    }

    public static void logInfoMsg(String msg) {
        updateInstance();
        LOGGER.info(msg);
    }

    public static void logError(String msg) {
        updateInstance();
        LOGGER.error(msg);
    }

    public static void logError(String msg, Exception e){
        updateInstance();
        LOGGER.error(msg, e);
    }
}

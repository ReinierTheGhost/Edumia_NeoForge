package com.legends.edumia.utils;

import com.legends.edumia.Edumia;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class EdumiaLog {
    private static Logger LOGGER;
    public static final org.slf4j.Logger LOGGER2 = LoggerFactory.getLogger(Edumia.MOD_ID);

    private static EdumiaLog singleInstance = null;

    private static synchronized EdumiaLog updateInstance() {
        if (singleInstance == null) singleInstance = new EdumiaLog();
        return singleInstance;
    }

    public EdumiaLog(){
    }

    public static void find() {
        LOGGER = LogManager.getLogger();
    }

    public static void debug(String s) {
        LOGGER.debug("edumia: " + s);
    }

    public static void debug(String s, Object... params) {
        LOGGER.debug(String.format("edumia: " + s, params));
    }

    public static void info(String s) {
        LOGGER.info("edumia: " + s);
    }

    public static void info(String s, Object... params) {
        LOGGER.info(String.format("edumia: " + s, params));
    }

    public static void warn(String s) {
        LOGGER.warn("edumia: " + s);
    }

    public static void warn(String s, Object... params) {
        LOGGER.warn(String.format("edumia: " + s, params));
    }

    public static void error(String s) {
        LOGGER.error("edumia: " + s);
    }

    public static void error(String s, Object... params) {
        LOGGER.error(String.format("edumia: " + s, params));
    }

    public static void logDebugMsg(String msg) {
        if(Edumia.IS_DEBUG)
            updateInstance();
        LOGGER2.info(msg);
    }

    public static void logInfoMsg(String msg) {
        updateInstance();
        LOGGER2.info(msg);
    }

    public static void logError(String msg) {
        updateInstance();
        LOGGER2.error(msg);
    }

    public static void logError(String msg, Exception e){
        updateInstance();
        LOGGER2.error(msg, e);
    }
}

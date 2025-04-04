package com.legends.edumia.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdumiaLog {
    private static Logger LOGGER;

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
}

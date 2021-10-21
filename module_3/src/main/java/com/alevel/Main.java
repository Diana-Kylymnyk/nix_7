package com.alevel;

import com.alevel.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    public static void main(String[] args) {
        try (Controller controller = new Controller(args[0], args[1])) {
            LOGGER_INFO.info("Main started.");
            controller.createOperation(args[2]);
            controller.exportData(args[2], args[3]);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("Error ", e);
        }
    }
}


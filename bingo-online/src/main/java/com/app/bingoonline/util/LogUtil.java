package com.app.bingoonline.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static com.app.bingoonline.util.LogContants.ERROR_TYPE;

public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public void createLog(String className, String methodName, String message, Object data){
        logger.info("class={}, method={}, message={}, data={}", className, methodName, message, data);
    }

    public void createLog(String className, String logType, String methodName, String message, Object data){
        logger.info("class={}, logType={}, method={}, message={}, data={}", className, logType, methodName, message, data);
    }

    public void createLog(String className, String logType, String methodName, String message, Instant dateTime, Object data){
        logger.info("class={}, logType={}, method={}, message={}, dateTime={}, data={}", className, logType, methodName, message,  dateTime, data);
    }

    public void createLog(String methodName, String message, Object data){
        logger.info("method={}, message={}, data={}", methodName, message, data);
    }

    public void createLog(String methodName, String logType, String message, Instant dateTime, Object data){
        logger.info("method={}, logType={}, message={}, dateTime={},  data={}", methodName,  logType, message, dateTime, data);
    }

    public void createLogError(String methodName, String message, Object data){
        logger.error("method={}, message={}, data={}", methodName, message, data);
    }

    public void createLogError(String methodName, String logType, String message, Instant dateTime, Object data){
        logger.error("method={}, logType={}, message={}, dateTime={}, data={}", methodName, ERROR_TYPE, message, dateTime, data);
    }
}

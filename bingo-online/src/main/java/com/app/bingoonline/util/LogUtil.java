package com.app.bingoonline.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);

    public void createLog(String className, String methodName, String message, Object data){
            logger.info("class={}, method={}, message={}, data={}", className, methodName, message, data);
    }

    public void createLog(String methodName, String message, Object data){
            logger.info("method={}, message={}, data={}", methodName, message, data);
    }

    public void createLogError(String methodName, String message, Object data){
        logger.error("method={}, message={}, data={}", methodName, message, data);
    }
}

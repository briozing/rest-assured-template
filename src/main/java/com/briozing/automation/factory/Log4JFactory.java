package com.briozing.automation.factory;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author KohitijDas
 */
public class Log4JFactory {

    private static Logger commonLogger;

    static {
        initCommonLogger();
    }

    private static void initCommonLogger() {
        if (commonLogger == null) {
            commonLogger = getLogger("Automation Log");
        }
    }

    public static Logger getCommonLogger() {
        initCommonLogger();
        return commonLogger;
    }

    /**
     * Every time it is going to give new instance. and log will get write into
     * Automation.log file always
     * <p>
     * it is useful when we need to log for DB, tests, redis, rabbit in such a way
     * we can identify from log file easily
     *
     * @param loggerName
     * @return
     */
    @SuppressWarnings("unused")
    public static Logger getLogger(String loggerName) {

        String fileName = System.getProperty("user.dir") + "/AutomationTestNG.log";
        File logFile = new File(fileName);
        Logger logger;
        logger = Logger.getLogger(loggerName);
        try {
            Properties logProperties = new Properties();
            logProperties.load(new FileInputStream("log4j.properties"));
            PropertyConfigurator.configure(logProperties);
            System.out.println("========== LOG4j Created " + loggerName + "============");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to load logging property :" + e);
        }

        return logger;
    }

}
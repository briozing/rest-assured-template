package com.briozing.automation.listners;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;

/**
 * @author Kohitij_Das
 */
public class ReportListner implements IReporter {

    private ExtentReports extent;
    private Map<String, String> systemInfo;
    private String jiraId = null;
    private String stepName = null;

    /**
     * @param xmlSuites
     * @param suites
     * @param outputDirectory
     */
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        generateExtentReport(xmlSuites, suites, outputDirectory);
    }

    /**
     * @param xmlSuites
     * @param suites
     * @param outputDirectory
     */
    private void generateExtentReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        extent = new ExtentReports(outputDirectory + File.separator + "ExtentSummaryReport.html", true);
        extent.loadConfig(new File("extent-config.xml"));
        extent.addSystemInfo(systemInfo);

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();

                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildDefectNode(context.getAllTestMethods(), LogStatus.WARNING);
            }
        }

        extent.flush();
        extent.close();
    }

    /**
     * @param tests
     * @param status
     */
    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
        String testCaseName = "";
        String dpParamName = "";
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                testCaseName = result.getMethod().getMethodName();
                if (result.getParameters().length > 0) {
                    dpParamName = Arrays.asList(result.getParameters()).toString();
                    testCaseName = getTestCaseName(testCaseName, dpParamName);
                }
                test = extent.startTest(testCaseName);
                test.getTest().setStartedTime(getTime(result.getStartMillis()));
                test.getTest().setEndedTime(getTime(result.getEndMillis()));

                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);

                if (result.getThrowable() != null) {
                    String message = result.getThrowable().getMessage();
                    test.log(status, message);
                } else {
                    for (String msg : Reporter.getOutput(result)) {
                        test.log(status, msg);
                    }
                }
                test.log(status, String.format(status.toString().toLowerCase() + "%s", "ed"));
                extent.endTest(test);
            }
        }
    }

    /**
     * @param methods
     */
    private void buildDefectNode(ITestNGMethod[] methods, LogStatus status) {
        ExtentTest test;

        if (methods.length > 0) {
            for (ITestNGMethod m1 : methods) {
                if (m1.getConstructorOrMethod().getMethod().isAnnotationPresent(DefectInfo.class)) {
                    test = extent.startTest(m1.getMethodName());
                    jiraId = m1.getConstructorOrMethod().getMethod().getAnnotation(DefectInfo.class).jirId();
                    stepName = m1.getConstructorOrMethod().getMethod().getAnnotation(DefectInfo.class).stepName();
                    String description = "JiraId: " + jiraId;
                    test.assignCategory("Open Issue's");
                    test.log(status, stepName, description);
                    extent.endTest(test);
                }
            }
        }
    }

    /**
     * @param millis
     * @return
     */
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    /**
     * @param testCaseName
     * @param paramName
     * @return
     */
    private String getTestCaseName(String testCaseName, String paramName) {
        return testCaseName.concat("_" + paramName);
    }
}
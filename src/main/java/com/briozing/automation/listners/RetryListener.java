package com.briozing.automation.listners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author KohitijDas
 */
public class RetryListener implements IAnnotationTransformer {

    /**
     *
     */
    @SuppressWarnings("rawtypes")
    public void transform(ITestAnnotation testannotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {

        IRetryAnalyzer retry = testannotation.getRetryAnalyzer();

        if (retry == null) {
            testannotation.setRetryAnalyzer(RetryFailedTest.class);
        }
    }
}

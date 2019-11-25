package com.briozing.automation.suites;

import com.briozing.automation.factory.Log4JFactory;
import com.briozing.automation.listners.ReportListner;
import com.briozing.automation.utils.AppAssert;
import com.briozing.automation.utils.MainUtils;
import com.briozing.automation.utils.TestSteps;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Listeners(ReportListner.class)
public class Smoke {

    private Logger logger = Log4JFactory.getLogger(this.getClass().getSimpleName());

    @BeforeClass(alwaysRun = true)
    public void setup() {
    }

    @Test(groups = {"sanity"})
    public void my_first_test_success() throws Exception {
        try {
            final Map<String, Boolean> testSteps = new HashMap<>();
            testSteps.put(TestSteps.TEST_STEP_GIVEN.name(), true);
            testSteps.put(TestSteps.TEST_STEP_WHEN.name(), true);
            testSteps.put(TestSteps.TEST_STEP_THEN.name(), true);

            validateTest(testSteps);
        } catch (Exception ex) {
            AppAssert.assertTrue(false, "Failure executing test");
            ex.printStackTrace();
            logger.info(ex);
        }
    }

    private void validateTest(Map<String, Boolean> testSteps) throws Exception {
        if (null != testSteps.get(TestSteps.TEST_STEP_GIVEN.name()) && testSteps.get(TestSteps.TEST_STEP_GIVEN.name())) {
            MainUtils.stepLog(logger, TestSteps.TEST_STEP_GIVEN.name());
        }
        if (null != testSteps.get(TestSteps.TEST_STEP_WHEN.name()) && testSteps.get(TestSteps.TEST_STEP_WHEN.name())) {
            MainUtils.stepLog(logger, TestSteps.TEST_STEP_WHEN.name());
        }
        if (null != testSteps.get(TestSteps.TEST_STEP_THEN.name()) && testSteps.get(TestSteps.TEST_STEP_THEN.name())) {
            MainUtils.stepLog(logger, TestSteps.TEST_STEP_THEN.name());
        }
    }
}

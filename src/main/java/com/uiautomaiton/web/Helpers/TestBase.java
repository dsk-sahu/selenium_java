package com.uiautomaiton.web.Helpers;


import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class TestBase {
    protected Browser Browser= null;
    protected Test Test=null;
    protected HashMap<String, String> TestData =null;
    private Logger logger = null;


    public TestBase() throws Throwable
    {
        logger = Logger.getLogger(this.getClass());
        logger.info("START : " + getTestCaseName());
        this.Browser = new Browser();
        this.Test = new Test();
        this.TestData = TestDataLoader.xmlTestDataLoader(getTestCaseName());
    }

    @AfterTest
    public void afterTest()
    {
        logger.info("END : " + getTestCaseName());
        Browser.close();
    }


    @BeforeTest
    public void beforeTest()
    {
        Test.testName = getTestCaseName();
    }

    public String getTestCaseName() {return this.getClass().getSimpleName();}

//region utils
    public String calculateDate(String days)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        System.out.println("Current Date: "+sdf.format(cal.getTime()));
        //Number of Days to add
        cal.add(Calendar.DAY_OF_MONTH, Integer.parseInt(days));
        //Date after adding the days to the current date
        String newDate = sdf.format(cal.getTime());
        //Displaying the new Date after addition of Days to current date
        System.out.println("Date after Addition: "+newDate);
        return newDate;
    }

    //endregion

}

package com.uiautomaiton.web.Helpers;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;
import java.util.ArrayList;
import java.util.Iterator;


public class Test {
  //  Browser browser;
public static String testName;
    private static Logger logger = null;
    public Test() throws Exception
    {
        logger = Logger.getLogger(this.getClass());
    }

    int  stepNo=0;
    public  void step(String msg,CommandExecutor  commandExecutor) throws Throwable  {

        try
        {
            stepNo++;
            commandExecutor.execute();
            Reporter.log(stepNo + ":" + msg );
            logger.info(stepNo+":"+msg);
        }
        catch (Throwable e)
        {
            String screenshotPath=System.getProperty("user.dir") + "\\Screenshots\\"+testName+stepNo+".png";
            String log = "<a href='"+"file:///"+screenshotPath+"'/>"+stepNo+":"+msg+"</a>";
            Reporter.log(log);
            Browser.takeScreenshot(screenshotPath);
            logger.info("Test failed : "+testName+"at "+stepNo);
            Assert.fail(e.getMessage());

        }

    }


    //region Assertions

    public  void isTrue(String description,CommandExecutorWithReturn  commandExecutor,boolean... ignoreReport) throws Throwable   {
        step(description,()->{ Assert.assertTrue((Boolean)commandExecutor.execute(),description);});

    }

    public  void areEqual(String description,Object expected, CommandExecutorWithReturn  commandExecutor) throws Throwable  {
        step(description,()->{Assert.assertEquals(expected,commandExecutor.execute(),description);});
    }

    public  void notEqual(String description,Object expected, CommandExecutorWithReturn  commandExecutor) throws Throwable  {
        step(description,()->{Assert.assertNotEquals(expected,commandExecutor.execute(),description);});
    }

    public  void notNull(String description,CommandExecutorWithReturn  commandExecutor) throws Throwable  {
        step(description,()->{Assert.assertNotNull(commandExecutor.execute(),description);});
    }
    //endregion



}

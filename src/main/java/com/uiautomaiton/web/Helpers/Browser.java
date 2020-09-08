package com.uiautomaiton.web.Helpers;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Browser {
    public HashMap<String, String> config = null;
    private static String driverDir = System.getProperty("user.dir") + "\\drivers";
    public static WebDriver driver=null;
    private long webDriverWaitTimeout=0;
    private long implicitlyWait=0;
    private long pageLoadTimeout=0;
    private long setScriptTimeout=0;
    private static Logger logger = null;

    public Browser()throws Exception
    {
    config = getConfigData();
    webDriverWaitTimeout = Long.parseLong(TestDataLoader.getConfigData().get("WebDriverWait"));
    implicitlyWait = Long.parseLong(TestDataLoader.getConfigData().get("implicitlyWait"));
    pageLoadTimeout = Long.parseLong(TestDataLoader.getConfigData().get("pageLoadTimeout"));
    setScriptTimeout = Long.parseLong(TestDataLoader.getConfigData().get("setScriptTimeout"));
    logger = Logger.getLogger(this.getClass());

    }
    public void openBrowser(BrowserType browser) throws Exception
    {
        logger.info("open Browser called");
        DesiredCapabilities desiredCapabilities = null;

            switch (browser) {
                case FIREFOX:
                    File gekoDriverPath = new File(driverDir, "//geckodriver.exe");
                    System.setProperty("webdriver.gecko.driver", String.valueOf(gekoDriverPath));
                    this.driver = new FirefoxDriver();
                    break;
                case CHROME:
                    System.out.println(driverDir);
                    File chromeDriverPath = new File(driverDir, "//chromedriver.exe");
                    System.setProperty("webdriver.chrome.driver", String.valueOf(chromeDriverPath));
                    this.driver = new ChromeDriver();
                    break;
                case IE:
                    File ieDriverPath = new File(driverDir, "//IEDriverServer.exe");
                    System.setProperty("webdriver.ie.driver", String.valueOf(ieDriverPath));
                    this.driver = new InternetExplorerDriver();
                    break;
//			case IEEDGE:
//				throw new Exception("IEEDGE has not yet been implemented");
                default:
                    throw new Exception(browser + " Browser has not been Implemented");
            }
            if (this.driver !=null)
            {
                this.driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
                this.driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout,TimeUnit.SECONDS);
                this.driver.manage().timeouts().setScriptTimeout(setScriptTimeout, TimeUnit.SECONDS);

                this.driver.manage().window().maximize();
                this.driver.navigate().to(TestDataLoader.getConfigData().get("URL"));
            }

    }

    public HashMap<String, String> getConfigData() throws Exception {
        HashMap<String, String> hashMap = null;
        hashMap = new TestDataLoader().getConfigData();
        return hashMap;
    }

    // region Mouse Actions

    public  void click(Map<Object,Object> locators) throws Exception
    {
        WebElement webElement;
        Set entrySet = locators.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Object,Object> entry = (Map.Entry<Object, Object>) it.next();
            try
            {
                By by=getLocator(entry.getKey().toString(),entry.getValue().toString());
                if((by!=null))
                {
                    webElement= new WebDriverWait(this.driver,webDriverWaitTimeout).until(ExpectedConditions.elementToBeClickable(by));
                    webElement.click();
                    logger.info("Clicked with : "+entry.getKey().toString()+":"+entry.getValue().toString());
                    break;
                }
            }
            catch (Exception e){
                if(!it.hasNext()) {
                    System.out.print(e.getMessage());
                    logger.info("Can not locate : " + entry.getKey().toString() + ":" + entry.getValue().toString() + ":" + e.getStackTrace());
                    throw new Exception(e.getMessage());
                }
                continue;

            }
        }
    }

    public  void sendKeys(Map<Object,Object> locators,String textToEnter) throws Exception
    {
        WebElement webElement;
        Set entrySet = locators.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Object,Object> entry = (Map.Entry<Object, Object>) it.next();
            try
            {
                By by=getLocator(entry.getKey().toString(),entry.getValue().toString());
                if((by!=null))
                {
                    webElement= new WebDriverWait(this.driver,webDriverWaitTimeout).until(ExpectedConditions.elementToBeClickable(by));
                    webElement.sendKeys(textToEnter);
                    logger.info("SendKeys with : "+entry.getKey().toString()+":"+entry.getValue().toString());
                    break;
                }
            }
            catch (Exception e){
                if(!it.hasNext()) {
                    System.out.print(e.getMessage());
                    logger.info("Can not locate : "+entry.getKey().toString()+":"+entry.getValue().toString()+":"+e.getStackTrace());
                    throw new Exception(e.getMessage());
                }
            }
        }

    }

    public  void clear(Map<Object,Object> locators) throws Exception
    {
        WebElement webElement;
        Set entrySet = locators.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Object,Object> entry = (Map.Entry<Object, Object>) it.next();
            try
            {
                By by=getLocator(entry.getKey().toString(),entry.getValue().toString());
                if((by!=null))
                {
                    webElement= new WebDriverWait(this.driver,webDriverWaitTimeout).until(ExpectedConditions.elementToBeClickable(by));
                    webElement.clear();
                    logger.info("Clear textbox with : "+entry.getKey().toString()+":"+entry.getValue().toString());
                    break;
                }
            }
            catch (Exception e){
                if(!it.hasNext()) {
                    System.out.print(e.getMessage());
                    logger.info("Can not locate : " + entry.getKey().toString() + ":" + entry.getValue().toString() + ":" + e.getStackTrace());
                    throw new Exception(e.getMessage());
                }
            }
        }

    }
    // endregion

    // region Keyboard Actions


    public  void selectByVisibleText(Map<Object,Object> locators,String textToEnter) throws Exception
    {
        WebElement webElement;
        Set entrySet = locators.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Object,Object> entry = (Map.Entry<Object, Object>) it.next();
            try
            {
                By by=getLocator(entry.getKey().toString(),entry.getValue().toString());
                if((by!=null))
                {
                    webElement= new WebDriverWait(this.driver,webDriverWaitTimeout).until(ExpectedConditions.elementToBeClickable(by));
                    Select dropdown = new Select(webElement);
                    dropdown.selectByVisibleText(textToEnter);
                    logger.info("Selected text : "+entry.getKey().toString()+":"+entry.getValue().toString());
                    break;
                }
            }
            catch (Exception e){
                if(!it.hasNext()) {
                    System.out.print(e.getMessage());
                    logger.info("Can not locate : "+entry.getKey().toString()+":"+entry.getValue().toString()+":"+e.getStackTrace());
                    throw new Exception(e.getMessage());
                }
            }
        }

    }
    //endregion

    //region Browser Actions
    public void close()
    {
        logger.info("Closing browser");
        driver.quit();
    }

    //endregion

    //region Other Actions

    public Boolean isOnPage(String pageName) throws Exception
    {
        logger.info("isOnPage called");
        return pageName.equalsIgnoreCase(driver.getTitle());
    }

    public  Boolean isElementPresent(Map<Object,Object> locators) throws Exception
    {
        WebElement webElement;
        Set entrySet = locators.entrySet();
        Iterator it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<Object,Object> entry = (Map.Entry<Object, Object>) it.next();
            try
            {
                By by=getLocator(entry.getKey().toString(),entry.getValue().toString());
                if((by!=null))
                {
                    webElement= new WebDriverWait(this.driver,webDriverWaitTimeout).until(ExpectedConditions.presenceOfElementLocated(by));
                    if(webElement !=null)
                        logger.info("Element located : "+entry.getKey().toString()+":"+entry.getValue().toString());
                        return true;
                }
            }
            catch (Exception e){
                if(!it.hasNext()) {
                    System.out.print(e.getMessage());
                    logger.info("Can not locate : "+entry.getKey().toString()+":"+entry.getValue().toString()+":"+e.getStackTrace());
                    throw new Exception(e.getMessage());
                }
            }
        }return false;
    }

    public boolean dummyMethod()
    {return false;}

    public By getLocator(String locatorType,String locatorValue)throws Exception
    {
        By by;
        switch (locatorType.toLowerCase())
        {
            case  "id" : by= By.id(locatorValue);break;
            case  "xpath" : by= By.xpath(locatorValue);break;
            case  "classname" : by= By.className(locatorValue);break;
            default: by=null; throw new Exception("Invalid locator");

        }
        return by;
    }

    public static void takeScreenshot(String path) throws Exception {

        logger.info("Screenshot taken");
        File screenShotName;
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        screenShotName = new File(path);
        FileUtils.copyFile(scrFile, screenShotName);
    }

    //endregion




}

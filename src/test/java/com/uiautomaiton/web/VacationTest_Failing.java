package com.uiautomaiton.web;

import com.uiautomaiton.web.Helpers.BrowserType;
import com.uiautomaiton.web.Helpers.TestBase;
import com.uiautomaiton.web.Locators.Locator;
import com.uiautomaiton.web.Locators.Pages;
import org.testng.annotations.Test;


public class VacationTest_Failing extends TestBase {

    public VacationTest_Failing() throws Throwable {
    }

    @Test
    public void vacation_testcase_failing() throws Throwable {
        Test.step("Launch browser",()->{Browser.openBrowser(BrowserType.CHROME);});
        Test.step("Click on Vacations menu",()-> Browser.click(Pages.Menu.vacations));
        Test.step("Deselect cars",()-> Browser.click(Pages.Vacations.selectedCars));
        Test.step("Enter Fly from "+TestData.get("flyFromCode"),()-> Browser.sendKeys(Pages.Vacations.flyFromTextbox,TestData.get("flyFromCode")));
        Test.step("Select Fly From " + TestData.get("flyFrom"),()-> Browser.click(Pages.dynamicLocator(Pages.Vacations.flyFromOptions,Locator.XPath,TestData.get("flyFrom"))));
        Test.step("Enter Fly To " + TestData.get("flyToCode"),()-> Browser.sendKeys(Pages.Vacations.flyToTextbox,TestData.get("flyToCode")));
        Test.step("Select Fly To " +TestData.get("flyTo"),()-> Browser.click(Pages.dynamicLocator(Pages.Vacations.flyToOptions,Locator.XPath,TestData.get("flyTo"))));
        Test.step("Clear departing date",()-> Browser.clear(Pages.Vacations.departingDate));
        Test.step("Select departing date "+calculateDate(TestData.get("departingDate")),()->Browser.sendKeys(Pages.Vacations.departingDate,calculateDate(TestData.get("departingDate"))));
        Test.step("Select departing time "+TestData.get("departingTime"),()->Browser.selectByVisibleText(Pages.Vacations.departingTime,TestData.get("departingTime")));
        Test.step("Clear returning date",()-> Browser.clear(Pages.Vacations.returningDate));
        Test.step("Select returning date "+calculateDate(TestData.get("returningDate")),()->Browser.sendKeys(Pages.Vacations.returningDate,calculateDate(TestData.get("returningDate"))));
        Test.step("Select departing time "+TestData.get("returningTime"),()->Browser.selectByVisibleText(Pages.Vacations.returningTime,TestData.get("returningTime")));
        Test.step("Find a deal",()-> Browser.click(Pages.Vacations.findADeal));
        Test.isTrue("Verify search result page has loaded",()-> Browser.isElementPresent(Pages.SearchResult.startbychoosingyourhotel));
        Test.isTrue("Verify at least one search result is present",()-> Browser.isElementPresent(Pages.SearchResult.searchResult));
    }
}

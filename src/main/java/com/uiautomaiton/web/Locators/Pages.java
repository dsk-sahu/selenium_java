package com.uiautomaiton.web.Locators;
import java.util.Map;

public class Pages  {
   public static Menu Menu = null;
   public static Vacations Vacations = null;
   public static SearchResult SearchResult = null;

   static {
      try {
         Vacations = new Vacations();
         Menu = new Menu();
         SearchResult = new SearchResult();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }


   public static Map<Object,Object> dynamicLocator(Map<Object,Object> locators,Locator locatorType,String textToReplace) throws Exception
   {
      String replacedLocatorValue= locators.get(locatorType).toString().replace("##StringToReplace##",textToReplace);
      locators.put(locatorType,replacedLocatorValue);
      return locators;

   }


}

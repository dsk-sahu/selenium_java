package com.uiautomaiton.web.Locators;
import java.util.HashMap;
import java.util.Map;


public class Vacations {
    public final Map<Object,Object> hotels= new HashMap<Object,Object>();
    public final Map<Object,Object> cars= new HashMap<Object,Object>();
    public final Map<Object,Object> selectedCars= new HashMap<Object,Object>();
    public final Map<Object,Object> flights= new HashMap<Object,Object>();

    public final Map<Object,Object> flyFromTextbox= new HashMap<Object,Object>();
    public final Map<Object,Object> flyFromOptions= new HashMap<Object,Object>();
    public final Map<Object,Object> flyToTextbox= new HashMap<Object,Object>();
    public final Map<Object,Object> flyToOptions= new HashMap<Object,Object>();

    public final Map<Object,Object> departingDate= new HashMap<Object,Object>();
    public final Map<Object,Object> departingTime= new HashMap<Object,Object>();
    public final Map<Object,Object> returningDate= new HashMap<Object,Object>();
    public final Map<Object,Object> returningTime= new HashMap<Object,Object>();

    public final Map<Object,Object> findADeal= new HashMap<Object,Object>();


    public Vacations()
    {
       hotels.put(Locator.XPath,"//button[contains(text(),'Hotel')]");
       cars.put(Locator.XPath,"//button[contains(text(),'Car')]");
       selectedCars.put(Locator.XPath,"//button[@class='hw-btn hw-btn-check'][contains(text(),'Car')]");
       flights.put(Locator.XPath,"//button[contains(text(),'Flight')]");

       flyFromTextbox.put(Locator.ID,"farefinder-package-origin-location-input");
       flyFromOptions.put(Locator.XPath,"//a[@title='##StringToReplace##']");
       flyToTextbox.put(Locator.ID,"farefinder-package-destination-location-input");
       flyToOptions.put(Locator.XPath,"//a[@title='##StringToReplace##']");

       departingDate.put(Locator.ID,"farefinder-package-startdate-input");
       departingTime.put(Locator.ID,"farefinder-package-pickuptime-input");
       returningDate.put(Locator.ID,"farefinder-package-enddate-input");
       returningTime.put(Locator.ID,"farefinder-package-dropofftime-input");

       findADeal.put(Locator.ID,"farefinder-package-search-button");

    }
}

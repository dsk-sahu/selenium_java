package com.uiautomaiton.web.Locators;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    public final Map<Object,Object> hotels= new HashMap<Object,Object>();
    public final Map<Object,Object> cars= new HashMap<Object,Object>();
    public final Map<Object,Object> flights= new HashMap<Object,Object>();
    public final Map<Object,Object> vacations= new HashMap<Object,Object>();

    public Menu()
    {
       hotels.put(Locator.XPath,"//a[@data-bdd='hotel']");
       cars.put(Locator.XPath,"//a[@data-bdd='cars']");
       flights.put(Locator.XPath,"//a[@data-bdd='flights']");
       vacations.put(Locator.XPath,"//a[@data-bdd='packages']");
    }
}

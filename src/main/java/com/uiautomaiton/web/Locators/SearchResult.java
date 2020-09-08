package com.uiautomaiton.web.Locators;
import java.util.HashMap;
import java.util.Map;

public class SearchResult {
    public final Map<Object,Object> startbychoosingyourhotel= new HashMap<Object,Object>();
    public final Map<Object,Object> searchResult= new HashMap<Object,Object>();

    public SearchResult()
    {
       startbychoosingyourhotel.put(Locator.ID,"hotelResultTitle");
       searchResult.put(Locator.XPath,"//div[@class='hidden spid automationTag']");
    }
}

package com.example.currencyconverter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "ValCurs")
public class ExchangeRate
{
    @Attribute(name = "Date")
    private String date;
    @Attribute(name = "name")
    private String name;
    @ElementList(inline = true)
    private ArrayList<Currency> currency;

    public String getDate()
    {
        return date;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Currency> getCurrency()
    {
        return currency;
    }

    public void setCurrency(ArrayList<Currency> currency)
    {
        this.currency = currency;
    }
}

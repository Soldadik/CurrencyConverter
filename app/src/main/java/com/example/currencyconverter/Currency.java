package com.example.currencyconverter;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Valute")
public class Currency
{
    @Attribute(name = "ID")
    private String ID;
    @Element(name = "NumCode")
    private String numCode;
    @Element(name = "CharCode")
    private String charCode;
    @Element(name = "Nominal")
    private String Nominal;
    @Element(name = "Name")
    private String Name;
    @Element(name = "Value")
    private String Value;

    public Currency(@Attribute(name = "ID") String ID, @Element(name = "NumCode") String nCode, @Element(name = "CharCode") String cCode, @Element(name = "Name") String Name, @Element(name = "Nominal") String Nominal, @Element(name = "Value") String Value)
    {
        this.ID = ID;
        this.numCode = nCode;
        this.charCode = cCode;
        this.Name = Name;
        this.Nominal = Nominal;
        this.Value = Value;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String ID)
    {
        this.ID = ID;
    }

    public String getNumCode()
    {
        return numCode;
    }

    public void setNumCode(String numCode)
    {
        this.numCode = numCode;
    }

    public String getCharCode()
    {
        return charCode;
    }

    public void setCharCode(String charCode)
    {
        this.charCode = charCode;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getNominal()
    {
        return Nominal;
    }

    public void setNominal(String nominal)
    {
        this.Nominal = nominal;
    }

    public String getValue()
    {
        return Value;
    }

    public void setValue(String value)
    {
        this.Value = value;
    }
}

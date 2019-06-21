package com.example.currencyconverter;

public class ConvertOperation
{
    private String firstValute;
    private String secondValute;
    private String firstValue;
    private String secondValue;
    private String dateOfCurrency;

    ConvertOperation(String firstValute, String secondValute, String firstValue, String secondValue, String dateOfCurrency)
    {
        this.firstValute = firstValute;
        this.secondValute = secondValute;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.dateOfCurrency = dateOfCurrency;
    }

    public String getFirstValute()
    {
        return firstValute;
    }

    public void setFirstValute(String firstValute)
    {
        this.firstValute = firstValute;
    }

    public String getSecondValute()
    {
        return secondValute;
    }

    public void setSecondValute(String secondValute)
    {
        this.secondValute = secondValute;
    }

    public String getFirstValue()
    {
        return firstValue;
    }

    public void setFirstValue(String firstValue)
    {
        this.firstValue = firstValue;
    }

    public String getSecondValue()
    {
        return secondValue;
    }

    public void setSecondValue(String secondValue)
    {
        this.secondValue = secondValue;
    }

    public String getDateOfCurrency()
    {
        return dateOfCurrency;
    }

    public void setDateOfCurrency(String dateOfCurrency)
    {
        this.dateOfCurrency = dateOfCurrency;
    }
}

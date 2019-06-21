package com.example.currencyconverter;

import android.util.Pair;
import android.widget.EditText;
import android.widget.Spinner;
import java.text.DecimalFormat;
import java.util.HashMap;

public class Converter
{
    private HashMap<String, Pair<Integer, Double>> V = new HashMap<>(); //<КОД, <НОМИНАЛ, КУРС>>

    public HashMap<String, Pair<Integer, Double>> getV()
    {
        return this.V;
    }

    public void setV(HashMap<String, Pair<Integer, Double>> v)
    {
        this.V = v;
    }

    private double CNV1(EditText ET, String valute1, String valute2)
    {
        double temp = Double.parseDouble(ET.getText().toString()) * this.V.get(valute1).second * this.V.get(valute1).first;
        temp = temp / this.V.get(valute2).second * this.V.get(valute2).first;
        return temp;
    }

    private double CNV2(EditText ET, String valute1, String valute2)
    {
        double temp = Double.parseDouble(ET.getText().toString()) / this.V.get(valute1).second * this.V.get(valute1).first;
        temp = temp * this.V.get(valute2).second * this.V.get(valute2).first;
        return temp;
    }

    private double CNV3(EditText ET, String valute)
    {
        return Double.parseDouble(ET.getText().toString()) * this.V.get(valute).second * this.V.get(valute).first;
    }

    private double CNV4(EditText ET, String valute)
    {
        return Double.parseDouble(ET.getText().toString()) / this.V.get(valute).second * this.V.get(valute).first;
    }

    public String ConvertS1(Spinner S1, Spinner S2, EditText ET1)
    {
        double temp = 0.0;
        DecimalFormat dFormat = new DecimalFormat("###.####");
        if (S1.getSelectedItem().toString().equals(S2.getSelectedItem().toString()))
        {
            temp = Double.parseDouble(ET1.getText().toString());
        }
        else if (S1.getSelectedItem().toString().equals("RUB"))
        {
            switch (S2.getSelectedItem().toString())
            {
                case "RUB":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "USD":
                    temp = CNV4(ET1, "USD");
                    break;
                case "EUR":
                    temp = CNV4(ET1, "EUR");
                    break;
                case "JPY":
                    temp = CNV4(ET1, "JPY");
                    break;
            }
        }
        else if (S1.getSelectedItem().toString().equals("USD"))
        {
            switch (S2.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV3(ET1, "USD");
                    break;
                case "USD":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "EUR":
                    temp = CNV1(ET1, "USD", "EUR");
                    break;
                case "JPY":
                    temp = CNV1(ET1, "USD", "JPY");
                    break;
            }
        }
        else if (S1.getSelectedItem().toString().equals("EUR"))
        {
            switch (S2.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV3(ET1, "EUR");
                    break;
                case "USD":
                    temp = CNV1(ET1, "EUR", "USD");
                    break;
                case "EUR":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "JPY":
                    temp = CNV1(ET1, "EUR", "JPY");
                    break;
            }
        }
        else
        {
            switch (S2.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV3(ET1, "JPY");
                    break;
                case "USD":
                    temp = CNV1(ET1, "JPY", "USD");
                    break;
                case "EUR":
                    temp = CNV1(ET1, "JPY", "EUR");
                    break;
                case "JPY":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
            }
        }
        return dFormat.format(temp);
    }

    public String ConvertS2(Spinner S1, Spinner S2, EditText ET1)
    {
        double temp = 0.0;
        DecimalFormat dFormat = new DecimalFormat("###.####");
        if (S2.getSelectedItem().toString().equals(S1.getSelectedItem().toString()))
        {
            temp = Double.parseDouble(ET1.getText().toString());
        }
        else if (S2.getSelectedItem().toString().equals("RUB"))
        {
            switch (S1.getSelectedItem().toString())
            {
                case "RUB":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "USD":
                    temp = CNV3(ET1, "USD");
                    break;
                case "EUR":
                    temp = CNV3(ET1, "EUR");
                    break;
                case "JPY":
                    temp = CNV3(ET1, "JPY");
                    break;
            }
        }
        else if (S2.getSelectedItem().toString().equals("USD"))
        {
            switch (S1.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV4(ET1, "USD");
                    break;
                case "USD":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "EUR":
                    temp = CNV2(ET1, "USD", "EUR");
                    break;
                case "JPY":
                    temp = CNV2(ET1, "USD", "JPY");
                    break;
            }
        }
        else if (S2.getSelectedItem().toString().equals("EUR"))
        {
            switch (S1.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV4(ET1, "EUR");
                    break;
                case "USD":
                    temp = CNV2(ET1, "EUR", "USD");
                    break;
                case "EUR":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
                case "JPY":
                    temp = CNV2(ET1, "EUR", "JPY");
                    break;
            }
        }
        else
        {
            switch (S1.getSelectedItem().toString())
            {
                case "RUB":
                    temp = CNV4(ET1, "JPY");
                    break;
                case "USD":
                    temp = CNV2(ET1, "JPY", "USD");
                    break;
                case "EUR":
                    temp = CNV2(ET1, "JPY", "USD");
                    break;
                case "JPY":
                    temp = Double.parseDouble(ET1.getText().toString());
                    break;
            }
        }
        return dFormat.format(temp);
    }
}

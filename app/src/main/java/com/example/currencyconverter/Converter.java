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

    public String Convert(EditText ET, Spinner S1, Spinner S2)
    {
        double temp = 0.0;
        DecimalFormat dFormat = new DecimalFormat("###.####");
        if(S1.getSelectedItem().toString().equals(S2.getSelectedItem().toString()))
        {
            temp = Double.parseDouble(ET.getText().toString());

        }
        else if(S1.getSelectedItem().toString().equals("RUB") && !S2.getSelectedItem().equals("RUB"))
        {
            temp = Double.parseDouble(ET.getText().toString()) / this.V.get(S2.getSelectedItem().toString()).second * this.V.get(S2.getSelectedItem().toString()).first;
        }
        else if(!S1.getSelectedItem().toString().equals("RUB") && S2.getSelectedItem().toString().equals("RUB"))
        {
            temp = Double.parseDouble(ET.getText().toString()) * this.V.get(S1.getSelectedItem().toString()).second / this.V.get(S1.getSelectedItem().toString()).first;
        }
        else
        {
            temp = Double.parseDouble(ET.getText().toString()) / this.V.get(S2.getSelectedItem().toString()).second * this.V.get(S2.getSelectedItem().toString()).first;
            temp = temp * Double.parseDouble(ET.getText().toString()) * this.V.get(S1.getSelectedItem().toString()).second * this.V.get(S1.getSelectedItem().toString()).first;
        }

        return dFormat.format(temp);
    }
}

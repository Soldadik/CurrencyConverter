package com.example.currencyconverter;

import android.app.Application;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

public class XMLParser
{
    String TAG = "XML_LOG";
    String tmp = "";
    String t1 = "", t2 = "";
    boolean status = false;
    /*
    try
    {
        XmlPullParser parser = getResources().getXml(R.xml.daily_utf8);

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT)
        {
        */
                /*if(parser.getEventType() == XmlPullParser.START_TAG && parser.getName().equals("Valute"))
                {
                    Valute.put(parser.getAttributeValue(1), parser.getAttributeValue(4));
                    Log.d(TAG, parser.getAttributeName(0) + "=" + parser.getAttributeValue(0));
                    Log.d(TAG, parser.getAttributeName(1) + "=" + parser.getAttributeValue(1));
                    Log.d(TAG, parser.getAttributeName(2) + "=" + parser.getAttributeValue(2));
                    Log.d(TAG, parser.getAttributeName(3) + "=" + parser.getAttributeValue(3));
                    Log.d(TAG, parser.getAttributeName(4) + "=" + parser.getAttributeValue(4));

                    Toast toast = Toast.makeText(getApplicationContext(),"Считано из XML!", Toast.LENGTH_SHORT);
                    toast.show();
                }*/

            /*switch (parser.getEventType())
            {
                case XmlPullParser.START_DOCUMENT:
                    Log.d(TAG, "Начало документа");
                    break;
                case XmlPullParser.START_TAG:
                    Log.d(TAG, "START_TAG: имя тега = " + parser.getName() + ", уровень = " + parser.getDepth() + ", число атрибутов = " + parser.getAttributeCount());
                    for (int i = 0; i < parser.getAttributeCount(); i++)
                    {
                        tmp = tmp + parser.getAttributeName(i) + " = " + parser.getAttributeValue(i) + ", ";
                    }
                    if (!TextUtils.isEmpty(tmp))
                    {
                        Log.d(TAG, "Атрибуты: " + tmp);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    Log.d(TAG, "END_TAG: имя тега = " + parser.getName());
                    break;
                case XmlPullParser.TEXT:
                    if(parser.getName().equals("CharCode") && !status)
                    {
                        t1 = parser.getText();
                        status = true;
                    }
                    if(parser.getName().equals("Value") && status)
                    {
                        status = false;
                        t2 = parser.getText();
                        Valute.put(t1, Double.parseDouble(t2));
                    }
                    Log.d(TAG, "текст = " + parser.getText());
                    break;
            }
            //if((t1.equals("USD") || t1.equals("EUR") || t1.equals("JYP")))
            // {
            //Valute.put(t1, Double.parseDouble(t2));
            //  }
            parser.next();

        }
    }
        catch (Throwable t)
    {

    }
    */
}

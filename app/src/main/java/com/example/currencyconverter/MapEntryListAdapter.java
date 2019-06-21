package com.example.currencyconverter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapEntryListAdapter extends ArrayAdapter<HashMap.Entry<String, Pair<Integer, Double>>>
{
    public MapEntryListAdapter (Context context, List<Map.Entry<String, Pair<Integer, Double>>> entryList)
    {
        super (context, R.layout.currency_item, entryList);
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent)
    {
        View currentItemView = convertView != null ? convertView :
                LayoutInflater.from (getContext ()).inflate (R.layout.currency_item, parent, false);

        Map.Entry<String, Pair<Integer, Double>> currentEntry = this.getItem (position);

        TextView textViewKey = currentItemView.findViewById (R.id.CurrencyListText1);
        TextView textViewValue1 = currentItemView.findViewById (R.id.CurrencyListText2);
        TextView textViewValue2 = currentItemView.findViewById (R.id.CurrencyListText3);

        textViewKey.setText(currentEntry.getKey() + ":");
        textViewValue1.setText(currentEntry.getValue().first.toString());
        textViewValue2.setText(currentEntry.getValue().second.toString());

        return currentItemView;
    }
}
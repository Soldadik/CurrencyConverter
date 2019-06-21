package com.example.currencyconverter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    private Context context;
    private List<ConvertOperation> operationList;

    public CustomAdapter(Context context, List<ConvertOperation> opList)
    {
        this.context = context;
        this.operationList = opList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.operation_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        holder.currency_1.setText(operationList.get(position).getFirstValute());
        holder.currency_2.setText(operationList.get(position).getSecondValute());
        holder.value_1.setText(operationList.get(position).getFirstValue());
        holder.value_2.setText(operationList.get(position).getSecondValue());
        holder.date.setText("На дату: " + operationList.get(position).getDateOfCurrency());
    }

    @Override
    public int getItemCount()
    {
        try
        {
            return operationList.size();
        }
        catch (Exception e)
        {
            Log.d("_EXCEPTION_CST_ADAPTER_", e.getMessage());
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView currency_1;
        public TextView currency_2;
        public TextView value_1;
        public TextView value_2;
        public TextView date;

        public ViewHolder(View itemView)
        {
            super(itemView);
            currency_1 = itemView.findViewById(R.id.TVCurrency_1);
            currency_2 = itemView.findViewById(R.id.TVCurrency_2);
            value_1 = itemView.findViewById(R.id.TVValue_1);
            value_2 = itemView.findViewById(R.id.TVValue_2);
            date = itemView.findViewById(R.id.TVDate);
        }
    }
}

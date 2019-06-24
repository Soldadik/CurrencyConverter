package com.example.currencyconverter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CustomAdapter adapter;
    private List<ConvertOperation> operationList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view);
        operationList = new ArrayList<>();

        try
        {
            operationList = JSONHelper.importFromJSON(getApplicationContext());
        }
        catch (Exception e)
        {
            Log.d("_EXCEPTION_IMPORT_HST_", e.getMessage());
        }

        try
        {
            if(operationList.size() == 0)
            {
                Toast.makeText(this, "История пуста!", Toast.LENGTH_SHORT).show();
            }
        }
        catch (NullPointerException e)
        {
            Log.d("_HISTORY_EXCEPTION_", e.getMessage());
            Toast.makeText(this, "История пуста!", Toast.LENGTH_SHORT).show();
        }

        linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CustomAdapter(this, operationList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.history_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        try
        {
            if(operationList.size() > 0)
            {
                operationList.clear();
                MainActivity.convertOperationList.clear();
                JSONHelper.exportToJSON(getApplicationContext(), operationList);
                Intent intent = this.getIntent();
                this.finish();
                startActivity(intent);
            }
        }
        catch (Exception e)
        {
            Log.d("_CLS_HISTORY_EXCEPTION_", e.getMessage());
        }
        return true;
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}

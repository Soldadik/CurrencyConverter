package com.example.currencyconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    HashMap<String, Pair<Integer, Double>> V = new HashMap<>(); //<КОД, <НОМИНАЛ, КУРС>>
    List<HashMap.Entry<String, Pair<Integer, Double>>> mListOfMapEntries; //Для вывода курсов валют в ListView
    static List<ConvertOperation> convertOperationList;
    List<Currency> currencyList;
    Converter converter = new Converter();

    long last_text_edit = 0;
    Timer timer = new Timer();
    final long DELAY = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText ET1 = findViewById(R.id.editText_1);
        final EditText ET2 = findViewById(R.id.editText_2);
        final EditText ETD = findViewById(R.id.editDate);
        final Spinner SP1 = findViewById(R.id.CurrencySpinner_1);
        final Spinner SP2 = findViewById(R.id.CurrencySpinner_2);
        final Button BTND = findViewById(R.id.confirmButton);
        final ListView listView = findViewById(R.id.currencyList);

        ETD.setText(getCurrentDate());

        convertOperationList = new ArrayList<>();
        //Проверка на наличие файла истории операций
        try
        {
            JSONHelper.checkHistoryFile(getApplicationContext());
        }
        catch (IOException e)
        {
            Log.d("_EXCEPTION_CREATE_FILE_", e.getMessage());
        }

        currencyList = new ArrayList<>();

        try
        {
            convertOperationList = JSONHelper.importFromJSON(getApplicationContext());
        }
        catch (Exception e)
        {
            Log.d("_EXCEPTION_IMPORT_MAIN_", e.getMessage());
        }

        BTND.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MyApp.getApi().getData(ETD.getText().toString()).enqueue(new Callback<ExchangeRate>()
                {
                    @Override
                    public void onResponse(Call<ExchangeRate> call, Response<ExchangeRate> response)
                    {
                        if (response.isSuccessful())
                        {
                            currencyList = response.body().getCurrency();
                            for (Currency currency : currencyList)
                            {
                                switch (currency.getCharCode())
                                {
                                    case "USD":
                                        V.put(currency.getCharCode(), new Pair<>(Integer.parseInt(currency.getNominal()), Double.parseDouble(currency.getValue().replace(',', '.'))));
                                        break;
                                    case "EUR":
                                        V.put(currency.getCharCode(), new Pair<>(Integer.parseInt(currency.getNominal()), Double.parseDouble(currency.getValue().replace(',', '.'))));
                                        break;
                                    case "JPY":
                                        V.put(currency.getCharCode(), new Pair<>(Integer.parseInt(currency.getNominal()), Double.parseDouble(currency.getValue().replace(',', '.'))));
                                        break;
                                }
                            }
                            Log.d("_WARNING_CURRENCY_SIZE_", Integer.toString(V.size()));

                            mListOfMapEntries = new ArrayList<>(V.entrySet());
                            MapEntryListAdapter mMapEntryListAdapter = new MapEntryListAdapter(getApplicationContext(), mListOfMapEntries);
                            listView.setAdapter(mMapEntryListAdapter);

                            String temp = ET1.getText().toString();
                            ET1.setText("");
                            ET1.setText(temp);
                            Toast.makeText(getApplicationContext(), "Данные получены", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Log.d("_NO_SUCCESS_RESPONSE_", response.message().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ExchangeRate> call, Throwable t)
                    {
                        //Log.d("_WARNING_", t.getCause().toString());
                        Log.d("_WARNING_RESPONSE_", t.getMessage());
                        Toast.makeText(getApplicationContext(), "Ошибка соединения", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        final Handler handler = new Handler();

        ET1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (ET1.length() >= 1)
                {
                    try
                    {
                        converter.setV(V);
                        ET2.setText(converter.Convert(ET1, SP1, SP2));
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Не удалось выполнить конвертацию", Toast.LENGTH_SHORT).show();
                        Log.d("_WARNING__CONVERSION_1_", e.getMessage());
                        e.printStackTrace();
                    }

                }
                else
                {
                    ET2.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });



        ET2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if(timer != null)
                {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() > 0)
                {
                    timer.cancel();
                    timer = new Timer();
                    timer.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            last_text_edit = System.currentTimeMillis();
                            ConvertOperation CO = new ConvertOperation(SP1.getSelectedItem().toString(), SP2.getSelectedItem().toString(), ET1.getText().toString(), ET2.getText().toString(), ETD.getText().toString());
                            if(convertOperationList.size() == 10)
                            {
                                convertOperationList.remove(0);
                                convertOperationList.add(CO);
                            }
                            else
                            {
                                convertOperationList.add(CO);
                            }
                            handler.postDelayed(input_finish_checker, 1000);
                        }
                    }, DELAY);

                }
            }
        });

        SP1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (ET1.length() > 0)
                {
                    try
                    {
                        converter.setV(V);
                        ET2.setText(converter.Convert(ET1, SP1, SP2));
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Не удалось выполнить конвертацию", Toast.LENGTH_SHORT).show();
                        Log.d("_WARNING__CONVERSION_2_", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        SP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (ET1.length() > 0)
                {
                    try
                    {
                        converter.setV(V);
                        ET2.setText(converter.Convert(ET1, SP1, SP2));
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Не удалось выполнить конвертацию", Toast.LENGTH_SHORT).show();
                        Log.d("_WARNING__CONVERSION_3_", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
        startActivity(intent);
        return true;
    }

    @Override
    public void onBackPressed()
    {
        this.finishAffinity();
    }

    private Runnable input_finish_checker = new Runnable()
    {
        @Override
        public void run()
        {
            if (System.currentTimeMillis() > (last_text_edit + 400))
            {
                boolean result = JSONHelper.exportToJSON(getApplicationContext(), convertOperationList);
                if (result)
                {
                    Toast.makeText(getApplicationContext(), "Операция сохранена", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Не удалось сохранить", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    private String getCurrentDate()
    {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");

        return df.format(c.getTime());
    }
}

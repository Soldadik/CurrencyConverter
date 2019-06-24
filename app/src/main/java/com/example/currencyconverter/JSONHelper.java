package com.example.currencyconverter;
import android.content.Context;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class JSONHelper
{
    private static final String FILE_NAME = "data.json";

    private static class OperationItems
    {
        private List<ConvertOperation> operationList;

        List<ConvertOperation> getList()
        {
            return operationList;
        }
        void setList(List<ConvertOperation> list)
        {
            this.operationList = list;
        }
    }

    static void checkHistoryFile(Context context) throws IOException
    {
        File file = context.getFileStreamPath(FILE_NAME);
        if(!file.exists())
        {
            file = new File(context.getFilesDir(), FILE_NAME);
        }
    }

    static boolean exportToJSON(Context context, List<ConvertOperation> dataList)
    {
        Gson gson = new Gson();
        OperationItems operationItems = new OperationItems();
        operationItems.setList(dataList);
        String jsonString = gson.toJson(operationItems);

        FileOutputStream fileOutputStream = null;

        try
        {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(jsonString.getBytes());
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(fileOutputStream != null)
            {
                try
                {
                    fileOutputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

        }
        return false;
    }

    static List<ConvertOperation> importFromJSON(Context context)
    {
        InputStreamReader streamReader = null;
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = context.openFileInput(FILE_NAME);
            streamReader = new InputStreamReader(fileInputStream);
            Gson gson = new Gson();
            OperationItems operationItems = gson.fromJson(streamReader, OperationItems.class);
            return operationItems.getList();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            if (streamReader != null)
            {
                try
                {
                    streamReader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

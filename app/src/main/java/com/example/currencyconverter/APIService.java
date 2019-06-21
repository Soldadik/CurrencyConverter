package com.example.currencyconverter;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService
{
    @GET("scripts/XML_daily.asp")
    Call<ExchangeRate> getData(@Query("date_req") String dateReg);
}
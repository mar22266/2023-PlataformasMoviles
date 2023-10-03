package com.example.lab;

import androidx.annotation.Dimension;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    TextView tv;
    String url="https://jsonplaceholder.typicode.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.tv);
        tv.setText("");

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        myapi api=retrofit.create(myapi.class);

        Call<List<model>> call=api.getmodels();

        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                List<model> data=response.body();
                for(int i=0; i<data.size();i++)
                    tv.append(" SL No:"+data.get(i).getId()+" \n Title :"+data.get(i).getTitle()+"\n\n\n");
            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {

            }
        });

    }
}
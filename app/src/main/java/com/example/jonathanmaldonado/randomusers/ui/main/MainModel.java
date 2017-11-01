package com.example.jonathanmaldonado.randomusers.ui.main;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.jonathanmaldonado.randomusers.data.Name;
import com.example.jonathanmaldonado.randomusers.data.RandomUsers;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public class MainModel {


    MainContract.View mainView;
    final static private String TAG= MainActivity.class.getSimpleName()+"_TAG";
    private String BASE_URL="https://randomuser.me/api/";
    OkHttpClient client;

    public RandomUsers getUserData() throws IOException {



        return okhttpget();
    }

    public RandomUsers okhttpget() throws IOException {
        client = new OkHttpClient.Builder().build();




        Request request = new Request.Builder().url(BASE_URL).build();

        Response response = client.newCall(request).execute();
        String resp= response.body().string();

        RandomUsers randomUser=null;

        try {

            Gson gson =new GsonBuilder().create();
            randomUser = gson.fromJson(resp, RandomUsers.class);



        }catch (JsonParseException e){
            e.printStackTrace();

        }
        if(randomUser==null){
            randomUser=okhttpget();
        }

        return randomUser;

    }



}

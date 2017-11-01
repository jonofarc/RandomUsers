package com.example.jonathanmaldonado.randomusers.ui.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.jonathanmaldonado.randomusers.data.Name;
import com.example.jonathanmaldonado.randomusers.data.RandomUsers;
import com.example.jonathanmaldonado.randomusers.data.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

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

    private List<Result> randomUserResults;

    private Bitmap imageBitMap;

    private String fullName;
    private String fullAddress;
    private String fullEmail;

    final static private String TAG= MainActivity.class.getSimpleName()+"_TAG";
    private String BASE_URL="https://randomuser.me/api/";
    OkHttpClient client;

    public void getUserData() throws IOException {

        okhttpget();
    }

    public void okhttpget() throws IOException {
        client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(BASE_URL).build();
        Response response = client.newCall(request).execute();
        String resp= response.body().string();
        RandomUsers randomUser=null;

        try {
//            randomUserResults.clear();
            Gson gson = new GsonBuilder().create();
            randomUser = gson.fromJson(resp, RandomUsers.class);

            randomUserResults = randomUser.getResults();
            // mainResultTV.setText(GResults.get(0).getName().toString());
            for (int i = 0; i < randomUserResults.size(); i++) {
                final int currentRandomUserIndex = i;
                Log.d(TAG, "onResponse for: " + randomUserResults.get(currentRandomUserIndex).getName().getFirst().toString());


                StringBuilder nameBuilder = new StringBuilder();
                nameBuilder.append("Full Name: " + randomUserResults.get(currentRandomUserIndex).getName().getFirst().toString() + " " + randomUserResults.get(currentRandomUserIndex).getName().getLast().toString());
                fullName = nameBuilder.toString();

                //build the user address
                StringBuilder addressBuilder = new StringBuilder();
                addressBuilder.append("Address: ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getStreet() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getCity() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getState() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getPostcode());
                fullAddress = addressBuilder.toString();


                StringBuilder emailBuilder = new StringBuilder();
                emailBuilder.append("Email: " + randomUserResults.get(currentRandomUserIndex).getEmail().toString());
                fullEmail = emailBuilder.toString();


                //retrieve the user picture
                URL url = new URL(randomUserResults.get(currentRandomUserIndex).getPicture().getLarge());
                final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageBitMap = bmp;




            } //end for GResults

        }catch (JsonParseException e){
            e.printStackTrace();

        }
        if(randomUser==null){
           okhttpget();
        }



    }
    public String getName(){
        return fullName;
    }
    public String getAddress(){
        return fullAddress;
    }
    public String getEmail(){
        return fullEmail;
    }
    public Bitmap getBMP(){
        return imageBitMap;
    }



}

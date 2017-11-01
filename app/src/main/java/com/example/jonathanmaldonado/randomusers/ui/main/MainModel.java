package com.example.jonathanmaldonado.randomusers.ui.main;

import retrofit2.Retrofit;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public class MainModel {

    final static private String TAG= MainActivity.class.getSimpleName()+"_TAG";
    private String BASE_URL="https://randomuser.me/api/";


    public String getUserData(){
        return "Rettriving Data...";
    }
}

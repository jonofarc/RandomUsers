package com.example.jonathanmaldonado.randomusers.ui.main;

import android.content.Context;
import android.graphics.Bitmap;

import com.example.jonathanmaldonado.randomusers.data.RandomUsers;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public interface MainContract {

    interface View{
        void updateMainProfile(Bitmap bmp, String name, String address, String email);


        Context getContext();
    }
    interface Presenter{


        void getRandomUser();
        void saveUser();

    }

}

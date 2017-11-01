package com.example.jonathanmaldonado.randomusers.ui.main;


import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

import com.example.jonathanmaldonado.randomusers.data.RandomUsers;

import java.io.IOException;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    final static private String TAG= MainPresenter.class.getSimpleName()+"_TAG";

    MainModel mainModel;
    MainContract.View mainView;


    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();
    }


    @Override
    public void getRandomUser() {

        final Handler mHandler = new Handler();
        final RandomUsers[] randomusers = new RandomUsers[0];

        new Thread(new Runnable() {
            @Override
            public void run () {


                try {
                    mainModel.getUserData();

                } catch (IOException e) {
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        Log.d(TAG, "run: "+mainModel.getName()+mainModel.getAddress()+mainModel.getEmail());
                        String name=mainModel.getName();
                        String address=mainModel.getAddress();
                        String email=mainModel.getEmail();
                        Bitmap bmp = mainModel.getBMP();

                        mainView.updateMainProfile(bmp,name,address, email);
                    }
                });
            }
        }).start();


        //mainView.updateMainProfile("Jon");

    }



}

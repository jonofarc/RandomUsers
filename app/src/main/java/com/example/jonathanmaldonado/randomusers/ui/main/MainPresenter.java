package com.example.jonathanmaldonado.randomusers.ui.main;


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
                    randomusers[0] =mainModel.getUserData();
                    Log.d(TAG, "run: "+ randomusers[0].getResults().get(0).getName().getFirst().toString());




                } catch (IOException e) {
                    e.printStackTrace();
                }

                mHandler.post(new Runnable() {
                    @Override
                    public void run () {
                        mainView.updateMainProfile(randomusers[0].getResults().get(1).getName().getFirst().toString());
                    }
                });
            }
        }).start();


        //mainView.updateMainProfile("Jon");

    }



}

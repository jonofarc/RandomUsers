package com.jonofarc.randomusers.ui.main;

import android.content.Context;
import android.graphics.Bitmap;

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

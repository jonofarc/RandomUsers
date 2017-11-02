package com.example.jonathanmaldonado.randomusers.ui.logIn;

import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public interface SavedUsersContract {
    interface View{
        void displayUsers(String name);



    }
    interface Presenter{


        void getSavedUsers();


    }
}

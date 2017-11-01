package com.example.jonathanmaldonado.randomusers.ui.main;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public interface MainContract {

    interface View{
        void updateMainProfile(String name);
    }
    interface Presenter{
        void getRandomUser();
    }
}

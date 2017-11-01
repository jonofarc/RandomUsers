package com.example.jonathanmaldonado.randomusers.ui.main;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public class MainPresenter implements MainContract.Presenter {
    MainModel mainModel;
    MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView = mainView;
        mainModel = new MainModel();
    }


    @Override
    public void getRandomUser() {

        String name = mainModel.getUserData();
        mainView.updateMainProfile("Jon");
    }

}

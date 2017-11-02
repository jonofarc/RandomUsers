package com.example.jonathanmaldonado.randomusers.ui.logIn;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public class SavedUsersPresenter implements SavedUsersContract.Presenter {

    SavedUsersModel savedUsersModel;
    SavedUsersContract.View savedUsersView;


    public SavedUsersPresenter(SavedUsersContract.View savedUsersView) {
        this.savedUsersView = savedUsersView;
        savedUsersModel=new SavedUsersModel();
    }

    @Override
    public void getSavedUsers() {

        savedUsersView.displayUsers(savedUsersModel.readUsers());
    }
}

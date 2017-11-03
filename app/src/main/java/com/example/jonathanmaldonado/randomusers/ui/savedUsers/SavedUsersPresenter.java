package com.example.jonathanmaldonado.randomusers.ui.savedUsers;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public class SavedUsersPresenter implements SavedUsersContract.Presenter {

    SavedUsersModel savedUsersModel;
    SavedUsersContract.View savedUsersView;


    public SavedUsersPresenter(SavedUsersContract.View savedUsersView) {
        this.savedUsersView = savedUsersView;
        savedUsersModel=new SavedUsersModel();
        savedUsersModel.setContext(savedUsersView.getContext());
    }

    @Override
    public void getSavedUsers() {

        //savedUsersView.displayUsers(savedUsersModel.readUsers());
        savedUsersView.setRecyclerView(savedUsersModel.readUsers());
    }
}

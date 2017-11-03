package com.example.jonathanmaldonado.randomusers.ui.savedUsers;

import android.content.Context;

import com.example.jonathanmaldonado.randomusers.data.SavedUser;

import java.util.List;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public interface SavedUsersContract {
    interface View{
        void displayUsers(String name);
        void setRecyclerView(List<SavedUser> mDataset);

        Context getContext();
    }
    interface Presenter{


        void getSavedUsers();


    }
}

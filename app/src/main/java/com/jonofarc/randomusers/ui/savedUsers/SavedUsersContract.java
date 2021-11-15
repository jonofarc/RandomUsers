package com.jonofarc.randomusers.ui.savedUsers;

import android.content.Context;

import com.jonofarc.randomusers.data.SavedUser;

import java.util.List;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public interface SavedUsersContract {
    interface View{

        void setRecyclerView(List<SavedUser> mDataset);

        Context getContext();
        
    }
    interface Presenter{
        void getSavedUsers();
    }
}

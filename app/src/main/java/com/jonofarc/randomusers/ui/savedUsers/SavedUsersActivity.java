package com.jonofarc.randomusers.ui.savedUsers;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jonofarc.randomusers.R;
import com.jonofarc.randomusers.data.SavedUser;
import com.jonofarc.randomusers.data.SavedUsersRecyclerViewAdapter;

import java.util.List;

public class SavedUsersActivity extends AppCompatActivity implements SavedUsersContract.View {
    SavedUsersPresenter savedUsersPresenter;
    TextView savedUsersTV;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_users);
        savedUsersPresenter = new SavedUsersPresenter(this);

        savedUsersPresenter.getSavedUsers();

    }


    @Override
    public Context getContext() {
        return this;
    }

    public void setRecyclerView(List<SavedUser> mDataset) {

        if (mDataset.size() > 0) {
            mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);


            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mRecyclerView.setHasFixedSize(true);

            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);


            mAdapter = new SavedUsersRecyclerViewAdapter(mDataset, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            Toast.makeText(this, "No users saved on data base", Toast.LENGTH_SHORT).show();
        }


    }


}

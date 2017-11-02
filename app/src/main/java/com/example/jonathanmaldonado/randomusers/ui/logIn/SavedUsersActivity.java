package com.example.jonathanmaldonado.randomusers.ui.logIn;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.jonathanmaldonado.randomusers.R;

public class SavedUsersActivity extends AppCompatActivity implements SavedUsersContract.View{
    SavedUsersPresenter savedUsersPresenter;
    TextView savedUsersTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_users);
        savedUsersPresenter= new SavedUsersPresenter(this);
        savedUsersTV= (TextView) findViewById(R.id.saved_users_result_tv);
        savedUsersPresenter.getSavedUsers();
    }

    @Override
    public void displayUsers(String name) {
        savedUsersTV.setText(name);
    }

    @Override
    public Context getContext() {
        return this;
    }
}

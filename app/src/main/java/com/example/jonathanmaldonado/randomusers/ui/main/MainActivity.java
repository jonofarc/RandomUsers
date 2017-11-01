package com.example.jonathanmaldonado.randomusers.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jonathanmaldonado.randomusers.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    MainPresenter mainPresenter;
    TextView userNameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameTV = (TextView) findViewById(R.id.user_name_tv);
        mainPresenter= new MainPresenter(this);
    }

    public void updateMainProfile(String name){
        userNameTV.setText(name);
    }

    public void getNewUser(View view) {
        mainPresenter.getRandomUser();
    }
}

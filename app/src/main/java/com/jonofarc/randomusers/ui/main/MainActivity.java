package com.jonofarc.randomusers.ui.main;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jonofarc.randomusers.R;
import com.jonofarc.randomusers.db.DBHelper;
import com.jonofarc.randomusers.ui.savedUsers.SavedUsersActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    final static private String TAG = MainActivity.class.getSimpleName() + "_TAG";
    MainPresenter mainPresenter;
    ImageView profilePictureIV;
    TextView userNameTV;
    TextView userAddressTV;
    TextView userEmailTV;

    private DBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new DBHelper(this);
        SQLiteDatabase database = helper.getWritableDatabase();

        userNameTV = (TextView) findViewById(R.id.user_name_tv);
        userAddressTV = (TextView) findViewById(R.id.user_Address_tv);
        userEmailTV = (TextView) findViewById(R.id.user_email_tv);
        profilePictureIV = (ImageView) findViewById(R.id.profile_picture_iv);
        mainPresenter = new MainPresenter(this);

        mainPresenter.getRandomUser();

    }

    public void updateMainProfile(Bitmap bmp, String name, String address, String email) {
        Log.d(TAG, "updateMainProfile: updating the name------");
        userNameTV.setText(name);
        userAddressTV.setText(address);
        userEmailTV.setText(email);
        profilePictureIV.setImageBitmap(bmp);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void getNewUser(View view) {
        mainPresenter.getRandomUser();
    }

    public void saveUser(View view) {
        mainPresenter.saveUser();
    }

    public void viewUsers(View view) {
        Intent intent = new Intent(MainActivity.this, SavedUsersActivity.class);
        startActivity(intent);
    }
}

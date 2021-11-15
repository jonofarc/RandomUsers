package com.example.jonathanmaldonado.randomusers.ui.userDetails;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jonathanmaldonado.randomusers.R;
import com.example.jonathanmaldonado.randomusers.data.SavedUsersRecyclerViewAdapter;

public class UserDetailsActivity extends AppCompatActivity {


    Bundle extras;
    ImageView IV_profilePicture;
    TextView TV_name;
    TextView TV_address;
    TextView TV_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);


        IV_profilePicture = (ImageView) findViewById(R.id.details_profile_picture_iv);
        TV_name = (TextView) findViewById(R.id.details_user_name_tv);
        TV_email = (TextView) findViewById(R.id.details_user_email_tv);
        TV_address = (TextView) findViewById(R.id.details_user_Address_tv);
        Intent intent = getIntent();
        if (intent != null) {
            extras = intent.getExtras();
            setData();
        } else {
            Toast.makeText(this, "Data Error", Toast.LENGTH_SHORT).show();
        }


    }

    private void setData() {

        IV_profilePicture.setImageBitmap((Bitmap) extras.get(SavedUsersRecyclerViewAdapter.SAVED_USER_RECYCLER_VIEW_EXTRA_BMP));
        TV_name.setText((String) extras.get(SavedUsersRecyclerViewAdapter.SAVED_USER_RECYCLER_VIEW_EXTRA_NAME));
        TV_address.setText((String) extras.get(SavedUsersRecyclerViewAdapter.SAVED_USER_RECYCLER_VIEW_EXTRA_ADDRESS));
        TV_email.setText((String) extras.get(SavedUsersRecyclerViewAdapter.SAVED_USER_RECYCLER_VIEW_EXTRA_EMAIL));

    }
}

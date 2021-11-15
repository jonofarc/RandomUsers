package com.jonofarc.randomusers.ui.savedUsers;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.jonofarc.randomusers.data.SavedUser;
import com.jonofarc.randomusers.db.DBHelper;
import com.jonofarc.randomusers.db.FeedReaderContract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public class SavedUsersModel {
    private static final String TAG = SavedUsersModel.class.getSimpleName() + "_TAG";
    private SQLiteDatabase database;


    private Context context;

    public void setContext(Context ctx) {
        context = ctx;
    }


    public List<SavedUser> readUsers() {

        DBHelper helper = new DBHelper(context);
        database = helper.getWritableDatabase();
        List<SavedUser> message = null;
        try {
            message = retriveUser();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }


    private List<SavedUser> retriveUser() throws IOException {


        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PICTURE_IMAGE
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS + "= ?";
        String[] selectionArg = {
                "Record title"
        };

        String sortOtder = FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME + "DESC";


        String whereClause;
        String[] whereArgs;


        whereClause = null;
        whereArgs = null;
        Cursor cursor = database.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,   //Table
                projection,             //Projection
                whereClause,                   //Selection (WHERE)
                whereArgs,                   //Values for selection
                null,                   //Group by
                null,                   //Filters
                null                    //Sort order

        );


        cursor.getCount();

        List<SavedUser> myResults = new ArrayList<SavedUser>();

        if (cursor.getCount() > 0) {
            //Toast.makeText(context, "retriving users", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "retriveUser: No users found");

        }
        while (cursor.moveToNext()) {

            /*
            long entryID =cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String entryAlias=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS));
            String entryFullName=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME));
            String entryAddress=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
            String entryEmail=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
            byte[] entryPictureImage=cursor.getBlob(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_PICTURE_IMAGE));
            */


            //newMessage += "User ID: "+ entryID+" \n Alias: "+ entryAlias+ " \n Full Name: "+ entryFullName+ " \n Address "+entryAddress+" \n Email "+entryEmail+" \n Picture URL "+entryPictureImage+"\n";

            SavedUser savedUser = new SavedUser();

            String entryFullName = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME));
            String entryAddress = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
            String entryEmail = cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));

            StringBuilder imageDir = new StringBuilder();
            imageDir.append(context.getApplicationInfo().dataDir + "/app_imageDir/");
            Bitmap bmp = loadImageFromStorage(imageDir.toString(), (entryFullName + ".jpg"));


            savedUser.setName(entryFullName.toString());
            savedUser.setBmp(bmp);
            savedUser.setEmail(entryEmail.toString());
            savedUser.setAddress(entryAddress);
            myResults.add(savedUser);

        }


        return myResults;


    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public int pixelsToDp(int dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        return (int) Math.ceil(dp * logicalDensity);
    }

    private Bitmap loadImageFromStorage(String path, String name) {

        try {
            File f = new File(path, name);
            return BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }


}

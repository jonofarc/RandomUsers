package com.example.jonathanmaldonado.randomusers.ui.logIn;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jonathanmaldonado.randomusers.DataBase.DBHelper;
import com.example.jonathanmaldonado.randomusers.DataBase.FeedReaderContract;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Jonathan Maldonado on 11/1/2017.
 */

public class SavedUsersModel {
    private static final String TAG = SavedUsersModel.class.getSimpleName()+"_TAG";
    private DBHelper helper;
    private SQLiteDatabase database;

    private Context context;
    public void setContext(Context ctx){
        context=ctx;
    }

    public String readUsers(){

        helper = new DBHelper(context);
        database = helper.getWritableDatabase();
        String message="No data found";
        try {
            message=retriveUser();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    private String retriveUser () throws IOException {


        /*// example

        String[] tableColumns = new String[] {
            "column1",
            "(SELECT max(column1) FROM table2) AS max"
        };
        String whereClause = "column1 = ? OR column1 = ?";
        String[] whereArgs = new String[] {
            "value1",
            "value2"
        };
        String orderBy = "column1";

        Cursor c = sqLiteDatabase.query(
            "table1",
            tableColumns,
            whereClause,
            whereArgs,
            null,
            null,
            orderBy
         );

        // since we have a named column we can do
        int idx = c.getColumnIndex("max");

         */

        String[] projection={
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME,
                FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS,
                FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,
                FeedReaderContract.FeedEntry.COLUMN_NAME_PICTURE_IMAGE
        };
        String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS+"= ?";
        String[] selectionArg = {
                "Record title"
        };

        String sortOtder = FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME+"DESC";

        //we check if there was a message we apply filters else we send null
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
        String newMessage="";

        if(cursor.getCount()>0){
            Toast.makeText(context, "retriving users", Toast.LENGTH_SHORT).show();
        }else{
            Log.d(TAG, "retriveUser: No users found");

        }
        while (cursor.moveToNext()){


            long entryID =cursor.getLong(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            String entryAlias=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ALIAS));
            String entryFullName=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME));
            String entryAddress=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS));
            String entryEmail=cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL));
            byte[] entryPictureImage=cursor.getBlob(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLUMN_NAME_PICTURE_IMAGE));





            newMessage += "User ID: "+ entryID+" \n Alias: "+ entryAlias+ " \n Full Name: "+ entryFullName+ " \n Address "+entryAddress+" \n Email "+entryEmail+" \n Picture URL "+entryPictureImage+"\n";

            /*
            Bitmap bmp = getImage(entryPictureImage);

            // Create fields where we will show our information
            LinearLayout searchLinearLayout=(LinearLayout) this.findViewById(R.id.searchActivityLayout);
            LinearLayout.LayoutParams tv_params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT

            );
            LinearLayout.LayoutParams iv_params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT

            );
            TextView tv;
            ImageView iv;

            iv=new ImageView(this);
            StringBuilder imageDir = new StringBuilder();
            imageDir.append(getApplicationInfo().dataDir+"/app_imageDir/");
            Bitmap IMbmp = loadImageFromStorage(imageDir.toString(),(entryAlias+".jpg"));


            //convert from DP to Pixels as I am unable to find other way to assign height in dp
            iv_params.height=pixelsToDp(250);
            iv_params.topMargin=pixelsToDp(50);
            iv.setLayoutParams(iv_params);
            // check if there is image on device internal memory if not we load from database
            if(IMbmp==null){
                iv.setImageBitmap(bmp);
            }else{
                iv.setImageBitmap(IMbmp);
            }

            searchLinearLayout.addView(iv);

            tv=new TextView(context);
            tv.setLayoutParams(tv_params);
            tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
            tv.setText("ID: "+entryID);
            searchLinearLayout.addView(tv);

            tv=new TextView(context);
            tv.setLayoutParams(tv_params);
            tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
            tv.setText("Alias: "+entryAlias);
            searchLinearLayout.addView(tv);

            tv=new TextView(context);
            tv.setLayoutParams(tv_params);
            tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
            tv.setText("Full Name: "+entryFullName);
            searchLinearLayout.addView(tv);

            tv=new TextView(context);
            tv.setLayoutParams(tv_params);
            tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
            tv.setText("Address: "+entryAddress);
            searchLinearLayout.addView(tv);

            tv=new TextView(context);
            tv.setLayoutParams(tv_params);
            tv.setTextAppearance(context, android.R.style.TextAppearance_Large);
            tv.setText("Email: "+entryEmail);
            searchLinearLayout.addView(tv);
            */




        }


        return newMessage;
        //alertTV.setText(newMessage);

    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    public int pixelsToDp(int dp){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float logicalDensity = metrics.density;
        int px = (int) Math.ceil(dp * logicalDensity);
        return px;
    }

    private Bitmap loadImageFromStorage(String path, String name)
    {

        try {
            File f=new File(path, name);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }

    }






}

package com.example.jonathanmaldonado.randomusers.ui.main;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.jonathanmaldonado.randomusers.db.DBHelper;
import com.example.jonathanmaldonado.randomusers.db.FeedReaderContract;
import com.example.jonathanmaldonado.randomusers.data.RandomUsers;
import com.example.jonathanmaldonado.randomusers.data.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Jonathan Maldonado on 10/31/2017.
 */

public class MainModel {

    private DBHelper helper;
    private SQLiteDatabase database;

    private List<Result> randomUserResults;

    private Bitmap imageBitMap;

    private String fullName;
    private String fullAddress;
    private String fullEmail;
    private Context context;

    final static private String TAG= MainActivity.class.getSimpleName()+"_TAG";
    private String BASE_URL="https://randomuser.me/api/";
    OkHttpClient client;

    public void getUserData() throws IOException {

        okhttpget();
    }
    public void setContext(Context ctx){
        context=ctx;
    }

    public void okhttpget() throws IOException {
        client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(BASE_URL).build();
        Response response = client.newCall(request).execute();
        String resp= response.body().string();
        RandomUsers randomUser=null;

        try {
//            randomUserResults.clear();
            Gson gson = new GsonBuilder().create();
            randomUser = gson.fromJson(resp, RandomUsers.class);

            randomUserResults = randomUser.getResults();

            for (int i = 0; i < randomUserResults.size(); i++) {
                final int currentRandomUserIndex = i;
                Log.d(TAG, "onResponse for: " + randomUserResults.get(currentRandomUserIndex).getName().getFirst().toString());


                StringBuilder nameBuilder = new StringBuilder();
                nameBuilder.append("Full Name: " + randomUserResults.get(currentRandomUserIndex).getName().getFirst().toString() + " " + randomUserResults.get(currentRandomUserIndex).getName().getLast().toString());
                fullName = nameBuilder.toString();

                //build the user address
                StringBuilder addressBuilder = new StringBuilder();
                addressBuilder.append("Address: ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getStreet() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getCity() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getState() + " ");
                addressBuilder.append(randomUserResults.get(currentRandomUserIndex).getLocation().getPostcode());
                fullAddress = addressBuilder.toString();


                StringBuilder emailBuilder = new StringBuilder();
                emailBuilder.append("Email: " + randomUserResults.get(currentRandomUserIndex).getEmail().toString());
                fullEmail = emailBuilder.toString();


                //retrieve the user picture
                URL url = new URL(randomUserResults.get(currentRandomUserIndex).getPicture().getLarge());
                final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageBitMap = bmp;




            } //end for GResults

        }catch (JsonParseException e){
            e.printStackTrace();

        }
        if(randomUser==null){
           okhttpget();
        }



    }
    public String getName(){
        return fullName;
    }
    public String getAddress(){
        return fullAddress;
    }
    public String getEmail(){
        return fullEmail;
    }
    public Bitmap getBMP(){
        return imageBitMap;
    }
    public List<Result> getRandomUserResults(){

        helper = new DBHelper(context);
        database = helper.getWritableDatabase();

        if(randomUserResults.size()<0){

            //TODO add no user logic

        }else{


            byte[] imageBitsArray=getBytes(imageBitMap);




            ContentValues values= new ContentValues();

            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_FULL_NAME,fullName);
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ADDRESS,fullAddress);
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_EMAIL,fullEmail);
            values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_PICTURE_IMAGE,imageBitsArray);
            long recordId = database.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);

            saveToInternalStorage(imageBitMap,fullName+".jpg");
            if (recordId>0){
                 Log.d(TAG, "Record Saved");
                //show if save was succesfull
                //  saveNoteResult.setText("");
                //alertTV.setText("User Saved: "+" \nAlias: "+ alias+" \nName: "+ originalFullName+ " \nAddress: "+ originalAddress+" \nEmail: "+ originalEmail+ " \npictureImage: "+ pictureImage);

            }


        }


        return randomUserResults;
    }
    public static byte[] getBytes(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String imageName){
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);

        //
        // Create imageDir
        File mypath=new File(directory,imageName);
        Toast.makeText(cw, mypath.getPath().toString(), Toast.LENGTH_SHORT).show();
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


}






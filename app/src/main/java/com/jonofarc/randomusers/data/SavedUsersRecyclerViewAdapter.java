package com.jonofarc.randomusers.data;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jonofarc.randomusers.R;
import com.jonofarc.randomusers.ui.userDetails.UserDetailsActivity;

import java.util.List;


/**
 * Created by Jonathan Maldonado on 9/9/2017.
 */

public class SavedUsersRecyclerViewAdapter extends RecyclerView.Adapter <SavedUsersRecyclerViewAdapter.ViewHolder> {
    public static final String SAVED_USER_RECYCLER_VIEW_EXTRA_BMP="com.jonofarc.randomusers.data.SAVED_USER_RECYCLER_VIEW_EXTRA_BMP";
    public static final String SAVED_USER_RECYCLER_VIEW_EXTRA_NAME="com.jonofarc.randomusers.data.SAVED_USER_RECYCLER_VIEW_EXTRA_NAME";
    public static final String SAVED_USER_RECYCLER_VIEW_EXTRA_ADDRESS="com.jonofarc.randomusers.data.SAVED_USER_RECYCLER_VIEW_EXTRA_ADDRESS";
    public static final String SAVED_USER_RECYCLER_VIEW_EXTRA_EMAIL="com.jonofarc.randomusers.data.SAVED_USER_RECYCLER_VIEW_EXTRA_EMAIL";

    private List<SavedUser> mDataset;
    Context mContext;

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int mPosition) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final int position = mPosition;

        holder.TV_name.setText(mDataset.get(position).getName());
        holder.IV_userImage.setImageBitmap(mDataset.get(position).getBmp());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, UserDetailsActivity.class);
                intent.putExtra(SAVED_USER_RECYCLER_VIEW_EXTRA_BMP, mDataset.get(position).getBmp());
                intent.putExtra(SAVED_USER_RECYCLER_VIEW_EXTRA_NAME, mDataset.get(position).getName());
                intent.putExtra(SAVED_USER_RECYCLER_VIEW_EXTRA_ADDRESS, mDataset.get(position).getAddress());
                intent.putExtra(SAVED_USER_RECYCLER_VIEW_EXTRA_EMAIL, mDataset.get(position).getEmail());
                mContext. startActivity(intent);
            }
        });
    }




    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TV_name;
        public ImageView IV_userImage;
        public CardView cardView;

        public ViewHolder(View v) {
            super(v);
            TV_name = v.findViewById(R.id.user_name_tv);
            IV_userImage=v.findViewById(R.id.user_image_iv);
            cardView=v.findViewById(R.id.user_cardview);



        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SavedUsersRecyclerViewAdapter(List<SavedUser> savedUsers, Context context) {



        mDataset = savedUsers;
        mContext=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {


        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);



        ViewHolder vh = new ViewHolder(v);
        return vh;
    }




    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

package com.example.jonathanmaldonado.randomusers.data;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonathanmaldonado.randomusers.R;
import com.example.jonathanmaldonado.randomusers.ui.userDetails.UserDetailsActivity;

import java.util.List;


/**
 * Created by Jonathan Maldonado on 9/9/2017.
 */

public class SavedUsersRecyclerViewAdapter extends RecyclerView.Adapter <SavedUsersRecyclerViewAdapter.ViewHolder> {
    public static final String SAVED_USER_RECYCLER_VIEW_EXTRA="com.example.jonathanmaldonado.randomusers.data.SAVED_USER_RECYCLER_VIEW_EXTRA";
    private List<SavedUser> mDataset;
    Context mContext;

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.TV_name.setText(mDataset.get(position).name);
        holder.IV_userImage.setImageBitmap(mDataset.get(position).bmp);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, UserDetailsActivity.class);
                intent.putExtra(SAVED_USER_RECYCLER_VIEW_EXTRA, mDataset.get(position).getClass());
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

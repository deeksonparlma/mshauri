package com.epicodus.mshauri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.epicodus.mshauri.R;
import com.epicodus.mshauri.model.AwarenessModel;
import com.epicodus.mshauri.model.FoundationModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoundationsAdapter extends RecyclerView.Adapter<FoundationsAdapter.FoundationViewHolder> {
    private Context mContext;
//    private String[] mLocation;
//    private String[] mNumber;
    private ArrayList<FoundationModel> mFoundation;
    public FoundationsAdapter(Context context, ArrayList<FoundationModel> foundation) {
        this.mContext = context;
        this.mFoundation = foundation;
    }

//    @Override
//    public Object getItem(int position) {
//        String restaurant = mLocation[position];
//        String cuisine = mNumber[position];
//        return String.format("%s \nContacts: %s", restaurant, cuisine);
//    }

//    @Override
//    public int getCount() {
//        return mFoundation.size();
//    }

    @Override
    public FoundationsAdapter.FoundationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foundationmodel,parent,false);
        FoundationsAdapter.FoundationViewHolder viewHolder =new FoundationViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( FoundationsAdapter.FoundationViewHolder holder, int position) {
        holder.bindFoundation(mFoundation.get(position));
    }

    @Override
    public int getItemCount() {
        return  mFoundation.size();
    }
    public class FoundationViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView5) TextView mPost;
        @BindView(R.id.textView6) TextView mStars;
        @BindView(R.id.name) TextView mName;
        public FoundationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext =itemView.getContext();
        }
        public void bindFoundation(FoundationModel foundation){
            mPost.setText(foundation.getPosts());
            mStars.setText(foundation.getStars());
            mName.setText(foundation.getLocation());
//            mLocation.setText("@"+foundation.getLocation());
//            mLocation.setText("@"+foundation.getLocation());
        }
    }
}

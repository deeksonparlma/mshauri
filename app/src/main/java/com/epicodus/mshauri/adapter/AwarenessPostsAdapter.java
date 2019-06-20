package com.epicodus.mshauri.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.epicodus.mshauri.R;
import com.epicodus.mshauri.model.AwarenessModel;
import com.epicodus.mshauri.model.FoundationModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AwarenessPostsAdapter extends RecyclerView.Adapter<AwarenessPostsAdapter.AwarenessViewHolder>{
    private Context mContext;
    private ArrayList<AwarenessModel> mAwareness;

    public AwarenessPostsAdapter(Context applicationContext, ArrayList<AwarenessModel> awareness) {
        mContext = applicationContext;
        mAwareness =awareness;
    }

    @Override
    public AwarenessPostsAdapter.AwarenessViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        LayoutInflater.from(parent.getContext()).inflate(R.layout.shimmer,parent,false);
//        ShimmerFrameLayout container = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);
//        container.startShimmer();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.awarenessmodel,parent,false);
        AwarenessPostsAdapter.AwarenessViewHolder viewHolder =new AwarenessViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( AwarenessPostsAdapter.AwarenessViewHolder holder, int position) {
        holder.bindAwarenessPosts(mAwareness.get(position));
    }

    @Override
    public int getItemCount() {
        return mAwareness.size();
    }

    public class AwarenessViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.AwarenesspostFoundation) TextView AwarenesspostFoundation;
        @BindView(R.id.AwarenesspostTitle) TextView AwarenesspostTitle;
        @BindView(R.id.AwarenesspostArticle) TextView AwarenesspostArticle;
        @BindView(R.id.AwarenesspostDate) TextView AwarenesspostDate;
        public AwarenessViewHolder(View View) {
            super(View);
            ButterKnife.bind(this,View);
            mContext =View.getContext();
        }
        public void bindAwarenessPosts(AwarenessModel awareness){
//            AwarenesspostFoundation.setText("@"+awareness.getFoundationName());
            AwarenesspostTitle.setText(awareness.getArticleTitile());
            AwarenesspostArticle.setText(awareness.getArticleContent());
            AwarenesspostDate.setText(awareness.getDatePosted());
        }
    }
}

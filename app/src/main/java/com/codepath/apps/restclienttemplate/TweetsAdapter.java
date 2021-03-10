package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.codepath.apps.restclienttemplate.models.Tweet;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class TweetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // The items to display in your RecyclerView
    Context context;
    List<Tweet> tweets;

    private final int NOMEDIA = 0, MEDIA = 1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        this.tweets = tweets;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (tweets.get(position).mediaURL.equals("")){
            return NOMEDIA;
        } else if (!tweets.get(position).mediaURL.equals("")){
            return MEDIA;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType){
            case NOMEDIA:
                View v1 = inflater.inflate(R.layout.item_tweet, viewGroup, false);
                viewHolder = new ViewHolder(v1);
                break;
            default:
                View v2 = inflater.inflate(R.layout.item_tweet_media, viewGroup, false);
                viewHolder = new ViewHolder1(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case NOMEDIA:
                ViewHolder vh1 = (ViewHolder) viewHolder;
                Tweet tweet = tweets.get(position);
                vh1.bind(tweet);
                break;
            default:
                ViewHolder1 vh2 = (ViewHolder1) viewHolder;
                Tweet tweet1 = tweets.get(position);
                vh2.bind(tweet1);
                break;
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return tweets.size();
    }

    //define a viewholder ///// VIEW FOR TWEETS WITHOUT IMAGES (NOMEDIA) ////////////////
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView relativeTime;
        TextView username;
        ImageView ivMedia;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            relativeTime = itemView.findViewById(R.id.relativeTime);
            username = itemView.findViewById(R.id.tvUsername);
            ivMedia = itemView.findViewById(R.id.media);
        }
        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            int radius = 30; // corner radius, higher value = more rounded
            int margin = 10;

            Glide.with(context).load(tweet.user.profileImageUrl).transform(new RoundedCornersTransformation(radius, margin)).into(ivProfileImage);
            relativeTime.setText(tweet.getFormattedTimestamp()); ///////////////////////////////////
            username.setText(String.format("@%s", tweet.user.name));
        }
    }
    //////////////    VIEW FOR TWEETS WITH IMAGES (MEDIA)       //////////////////////////////
    public class ViewHolder1 extends RecyclerView.ViewHolder{
        ImageView ivProfileImage;
        TextView tvBody;
        TextView tvScreenName;
        TextView relativeTime;
        TextView username;
        ImageView ivMedia;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            tvBody = itemView.findViewById(R.id.tvBody);
            tvScreenName = itemView.findViewById(R.id.tvScreenName);
            relativeTime = itemView.findViewById(R.id.relativeTime);
            username = itemView.findViewById(R.id.tvUsername);
            ivMedia = itemView.findViewById(R.id.media);
        }

        public void bind(Tweet tweet) {
            tvBody.setText(tweet.body);
            tvScreenName.setText(tweet.user.screenName);
            int radius = 30; // corner radius, higher value = more rounded
            int margin = 10;

            Glide.with(context).load(tweet.user.profileImageUrl).transform(new RoundedCornersTransformation(radius, margin)).into(ivProfileImage);
            relativeTime.setText(tweet.getFormattedTimestamp()); ///////////////////////////////////
            username.setText(String.format("@%s", tweet.user.name));
            Glide.with(context).load(tweet.mediaURL).transform(new RoundedCornersTransformation(radius, margin)).into(ivMedia);
        }
    }

    public void addAll(List<Tweet> tweetList){
        tweets.addAll(tweetList);
        notifyDataSetChanged();
    }

    public void clear(){
        tweets.clear();
        notifyDataSetChanged();
    }
}
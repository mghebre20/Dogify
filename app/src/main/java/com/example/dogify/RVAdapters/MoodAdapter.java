package com.example.dogify.RVAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogify.DetailsActivity1;
import com.example.dogify.Models.MoodPlaylist;
import com.example.dogify.R;

import org.parceler.Parcels;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.ViewHolder> implements View.OnClickListener {

    public static final String TAG = "MoodAdapter";

    private Context context;
    private ArrayList<MoodPlaylist> moodPlaylists;

    public MoodAdapter(Context context, ArrayList<MoodPlaylist> moodPlaylists) {
        this.context = context;
        this.moodPlaylists = moodPlaylists;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mood, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         MoodPlaylist moodPlaylist = moodPlaylists.get(position);
         holder.bind(moodPlaylist);
    }

    @Override
    public int getItemCount() {
        return moodPlaylists.size();
    }

    @Override
    public void onClick(View view) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMoodImage;
        TextView tvMoodText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMoodImage = itemView.findViewById(R.id.ivMoodImage);
            tvMoodText = itemView.findViewById(R.id.tvMoodText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    MoodPlaylist moodPlaylist = moodPlaylists.get(position);
                    Intent i = new Intent(context, DetailsActivity1.class);
                    i.putExtra(MoodPlaylist.class.getSimpleName(), Parcels.wrap(moodPlaylist));
                    context.startActivity(i);

                }
            });
        }

        public void bind(MoodPlaylist moodPlaylist) {
            tvMoodText.setText(moodPlaylist.getTitle());
            Glide.with(context).load(moodPlaylist.getImage()).into(ivMoodImage);
        }
    }
}

package com.example.dogify.RVAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogify.R;

import java.util.ArrayList;

public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.ViewHolder> {

    public static final String TAG = "MoodAdapter";

    private Context context;
    private ArrayList<String> moodNames = new ArrayList<>();
    private ArrayList<Integer> moodImage = new ArrayList<>();

    public MoodAdapter(Context context, ArrayList<String> moodNames, ArrayList<Integer> moodImage) {
        this.context = context;
        this.moodNames = moodNames;
        this.moodImage = moodImage;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mood, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(moodImage.get(position))
                .into(holder.ivMoodImage);

        holder.tvMoodText.setText(moodNames.get(position));
    }

    @Override
    public int getItemCount() {
        return moodNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivMoodImage;
        TextView tvMoodText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMoodImage = itemView.findViewById(R.id.ivMoodImage);
            tvMoodText = itemView.findViewById(R.id.tvMoodText);
        }
    }
}

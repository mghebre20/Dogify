package com.example.dogify;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dogify.Fragments.HomeFragment;
import com.example.dogify.Models.MoodPlaylist;

import org.parceler.Parcels;

public class DetailsActivity1 extends AppCompatActivity {

    private Context context;
    private TextView tvDAMood;
    private ImageView ivDAMood;
    private MoodPlaylist moodPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        getSupportActionBar().setTitle("");

        tvDAMood = findViewById(R.id.tvDAMood);
        ivDAMood = findViewById(R.id.ivDAMood);

        moodPlaylist = Parcels.unwrap(getIntent().getParcelableExtra(MoodPlaylist.class.getSimpleName()));
        tvDAMood.setText(moodPlaylist.getTitle());
        ivDAMood.setImageResource(moodPlaylist.getImage());
    }
}
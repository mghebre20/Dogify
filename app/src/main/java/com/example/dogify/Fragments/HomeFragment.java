package com.example.dogify.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogify.MainActivity;
import com.example.dogify.Models.MoodPlaylist;
import com.example.dogify.R;
import com.example.dogify.RVAdapters.MoodAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";

    private ArrayList<MoodPlaylist> moodPlaylists;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Home");

        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.i(TAG, "RecyclerView initialized");
        RecyclerView rvMood = getView().findViewById(R.id.rvMood);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvMood.setLayoutManager(linearLayoutManager);
        moodPlaylists = new ArrayList<>();
        setMoodPlaylists();
        MoodAdapter moodAdapter = new MoodAdapter(getContext(), moodPlaylists);
        rvMood.setAdapter(moodAdapter);
        moodAdapter.notifyDataSetChanged();
    }

    private void setMoodPlaylists(){

        MoodPlaylist mp1 = new MoodPlaylist();
        mp1.setTitle("Happy");
        mp1.setImage(R.mipmap.happy_dog_foreground);
        moodPlaylists.add(mp1);

        MoodPlaylist mp2 = new MoodPlaylist();
        mp2.setTitle("Sad");
        mp2.setImage(R.mipmap.sad_dog_foreground);
        moodPlaylists.add(mp2);

        MoodPlaylist mp3 = new MoodPlaylist();
        mp3.setTitle("Excited");
        mp3.setImage(R.mipmap.excited_dog_foreground);
        moodPlaylists.add(mp3);

        MoodPlaylist mp4 = new MoodPlaylist();
        mp4.setTitle("loud");
        mp4.setImage(R.mipmap.loud_dog_foreground);
        moodPlaylists.add(mp4);

        MoodPlaylist mp5 = new MoodPlaylist();
        mp5.setTitle("calm");
        mp5.setImage(R.mipmap.calm_dog_foreground);
        moodPlaylists.add(mp5);
    }
}
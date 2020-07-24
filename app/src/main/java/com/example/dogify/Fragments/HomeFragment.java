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
import com.example.dogify.R;
import com.example.dogify.RVAdapters.MoodAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.From azhareus to Everyone: (12:11 PM)
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    public static final String TAG = "HomeFragment";

    private ArrayList<String> moodNames = new ArrayList<>();
    private ArrayList<Integer> moodImage = new ArrayList<Integer>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((MainActivity)getActivity()).getSupportActionBar();
        actionBar.setTitle("Home");

        getImages();

    }
    private void getImages() {
        Log.i(TAG, "bitmaps images demonstrated");

        moodImage.add(0, R.mipmap.happy_dog_foreground);
        moodNames.add("Happy");

        moodImage.add(1, R.mipmap.sad_dog_foreground);
        moodNames.add("Sad");

        moodImage.add(2, R.mipmap.excited_dog_foreground);
        moodNames.add("Excited");

        moodImage.add(3, R.mipmap.loud_dog_foreground);
        moodNames.add("Loud");

        moodImage.add(4, R.mipmap.calm_dog_foreground);
        moodNames.add("Calm");

        initRecyclerView();
    }
    private void initRecyclerView() {
        Log.i(TAG, "initialize RecyclerView ");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView rvMood = getView().findViewById(R.id.rvMood);
        rvMood.setLayoutManager(linearLayoutManager);
        MoodAdapter moodAdapter = new MoodAdapter(getContext(), moodNames, moodImage);
        rvMood.setAdapter(moodAdapter);
    }
}
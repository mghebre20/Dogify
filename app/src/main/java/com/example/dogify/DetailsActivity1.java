package com.example.dogify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dogify.Models.MoodPlaylist;
import com.example.dogify.Models.SoundEffect;
import com.example.dogify.RVAdapters.SoundEffectAdapter;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.example.dogify.LoginActivity.SHARED_PREFS;

public class DetailsActivity1 extends AppCompatActivity {

    public static final String TAG = "DetailsActivity1";

    private TextView tvDAMood;
    private ImageView ivDAMood;
    private MoodPlaylist moodPlaylist;
   private ImageButton ibPlayButton;
    private TextView tvDASoundEffect;
    protected List<PlaylistTrack> soundEffects;
    protected SoundEffectAdapter soundEffectAdapter;
    private RecyclerView rvDASoundEffects;
    private SpotifyAppRemote spotifyAppRemote;
    public static final String USER_ID = "94bcjigka46hxnpx5y5ap1bjc";
    public static String PLAYLIST_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);
        getSupportActionBar().setTitle("");

        tvDAMood = findViewById(R.id.tvDAMood);
        ivDAMood = findViewById(R.id.ivDAMood);
        tvDASoundEffect = findViewById(R.id.tvDASoundEffect);
        ibPlayButton = findViewById(R.id.ibPlayButton);

        moodPlaylist = Parcels.unwrap(getIntent().getParcelableExtra(MoodPlaylist.class.getSimpleName()));
        tvDAMood.setText(moodPlaylist.getTitle());
        ivDAMood.setImageResource(moodPlaylist.getImage());
        PLAYLIST_ID = moodPlaylist.getPlaylistId();

//        ibPlayButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ibPlayButton.setBackgroundResource(R.mipmap.pause_button);
//
//            }
//        });

        initRecyclerView();
//        setPlaylistTracks();

    }

    private void initRecyclerView() {
        Log.i(TAG, "RecyclerView created and initialized!");
        rvDASoundEffects = findViewById(R.id.rvDASoundEffects);
        soundEffects = new ArrayList<>();
        soundEffectAdapter = new SoundEffectAdapter(this, soundEffects);
        rvDASoundEffects.setAdapter(soundEffectAdapter);
        soundEffectAdapter.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDASoundEffects.setLayoutManager(linearLayoutManager);
        setMoodPlaylistTracks();
    }

    protected void  setMoodPlaylistTracks() {

        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String token = sharedPref.getString("token", "");
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(token);
        SpotifyService spotifyService = spotifyApi.getService();




        //playlist tracks
            spotifyService.getPlaylistTracks(USER_ID, PLAYLIST_ID, new Callback<Pager<PlaylistTrack>>() {
                @Override
                public void success(Pager<PlaylistTrack> playlistTracks1, Response response) {
                    Log.d(TAG, playlistTracks1.toString());

                    soundEffectAdapter.addAll(playlistTracks1.items);
                    soundEffectAdapter.notifyDataSetChanged();
                    System.out.println(soundEffects.containsAll(playlistTracks1.items));
                    System.out.println(soundEffects);
                }
                @Override
                public void failure(RetrofitError error) {
                    if (error != null) {
                        Log.d(TAG, error.toString());
                        return;
                    }
                }
            });
    }

    //        try {
//        } catch (Exception e) {
//            Log.e(TAG, "setMoodPlaylistTracks: failure", e.getCause());
//        }
    //   System.out.println((playlistTracks).items.get(0).track.name);
//                    PlaylistTrack mp1 = new PlaylistTrack();
//                    mp1.a
//                (playlistTracks.items);
//                    soundEffects.containsAll(playlistTracks1.items);
//                    soundEffects.addAll(playlistTracks.items);
//                    soundEffectAdapter.notifyDataSetChanged();

//        //happy
//        MoodPlaylist mp1 = new MoodPlaylist();
//        Playlist userPlaylist1 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "3AFEKORzohS4lYtaV3bnMk");
//        mp1.setPlaylist("94bcjigka46hxnpx5y5ap1bjc", "3AFEKORzohS4lYtaV3bnMk",  new Callback<Playlist>() {
//            @Override
//            public void success(Playlist playlist, retrofit.client.Response response) {
//                Log.d(TAG, playlist.name);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Log.d(TAG, error.toString());
//            }
//        });
//        mp1.setSoundEffectTrack(spotifyService.getPlaylistTracks("94bcjigka46hxnpx5y5ap1bjc", "3AFEKORzohS4lYtaV3bnMk").items.get(0).track.name);
//        mp1.setImage(R.mipmap.play_button);
//
//        soundEffects.add(mp1);


//        SpotifyClient spotifyClient = new SpotifyClient();
//        spotifyClient.getAPlaylist("3AFEKORzohS4lYtaV3bnMk");
//        spotifyClient.getAPlaylistTracks("3AFEKORzohS4lYtaV3bnMk");
//
//        spotifyAppRemote.getPlayerApi().play();



//        //happy
//        Playlist userPlaylist1 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "3AFEKORzohS4lYtaV3bnMk");
//                System.out.println(userPlaylist1.tracks.items.get(0).track.name);

//        //sad
//        Playlist userPlaylist2 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "5vgVj1ZVXwUf7EAWqWW1J1");
//        userPlaylist2.tracks.items.get(1).track.name ;
//
//        //excited
//        Playlist userPlaylist3 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "1ZOcQMymKnnKUzQ1PkPTkp");
//        userPlaylist3.tracks.items.get(2).track.name ;
//
//        //loud
//        Playlist userPlaylist4 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "4GGOdKnGgFKl0aSP01eytt");
//        userPlaylist4.tracks.items.get(3).track.name ";
//
//        //calm
//        Playlist userPlaylist5 = spotifyService.getPlaylist("94bcjigka46hxnpx5y5ap1bjc", "7HTzRuMPwmgZQLUMiPlouf");
//        userPlaylist5.tracks.items.get(4).track.name ;


}
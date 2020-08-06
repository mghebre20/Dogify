package com.example.dogify;

import android.content.SharedPreferences;
import android.nfc.Tag;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.dogify.Models.User;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import retrofit.Callback;
import retrofit.RetrofitError;

import static android.content.Context.MODE_PRIVATE;
import static com.example.dogify.LoginActivity.SHARED_PREFS;
import static com.parse.Parse.getApplicationContext;

public class SpotifyClient {
    private static final String TAG = "SpotifyClient";
    private static final String CURRENT_USER_ENDPOINT = "https://api.spotify.com/v1/me/";
    private static final String CLIENT_ID = "27325e06a80b40bbadb9cd01ac815115";
    private static final String REDIRECT_URI = "com.example.dogify://callback";
    private static final int REQUEST_CODE = 15;
    private static final String USER_ID = "94bcjigka46hxnpx5y5ap1bjc";
    private RequestQueue queue;

    public SpotifyClient() {
    }


    public interface VolleyCallBack {
        void onSuccess();
    }

    public RequestQueue getRequestQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }

    //get request for current user
    public void getCurrentUser(final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(CURRENT_USER_ENDPOINT, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                User user = gson.fromJson(response.toString(), User.class);
                System.out.println(user.getUser());
                callBack.onSuccess();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Error on request " + error.getMessage());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                String token = sharedPref.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                Log.i(TAG, "got header!");
                return headers;
            }
        };
        queue = getRequestQueue();
        queue.add(jsonObjectRequest);
    }

    //get request for playlist
    public void getAPlaylist(String playlistID) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String token = sharedPref.getString("token", "");
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(token);
        SpotifyService spotifyService = spotifyApi.getService();
        spotifyService.getPlaylist(USER_ID, playlistID, new Callback<Playlist>() {
            @Override
            public void success(Playlist playlist, retrofit.client.Response response) {
                Log.d(TAG, playlist.name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });
    }

    //get request for playlist tracks
    public void getAPlaylistTracks(String playlistID) {
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String token = sharedPref.getString("token", "");
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(token);
        SpotifyService spotifyService = spotifyApi.getService();
        spotifyService.getPlaylistTracks(USER_ID, playlistID, new Callback<Pager<PlaylistTrack>>() {
            @Override
            public void success(Pager<PlaylistTrack> playlistTrackPager, retrofit.client.Response response) {
                Log.d(TAG, playlistTrackPager.items.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.toString());
            }
        });
    }
}


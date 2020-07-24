package com.example.dogify;

import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dogify.Models.User;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class SpotifyClient {
    private static final String BASE_URL = "https://api.spotify.com/v1/me";
    private SharedPreferences sharedPreferences;
    private RequestQueue queue;
    private User user;

    public SpotifyClient(RequestQueue queue, SharedPreferences sharedPreferences) {
        this.queue = queue;
        this.sharedPreferences = sharedPreferences;
    }

    public interface VolleyCallBack {
        void onSuccess();
    }

    public User getUser() {
        return user;
    }

    public void get(final VolleyCallBack callBack) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(BASE_URL, null, response -> {
            Gson gson = new Gson();
            user = gson.fromJson(response.toString(), User.class);
            callBack.onSuccess();
        }, error -> get(() -> {
        })) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String token = sharedPreferences.getString("token", "");
                String auth = "Bearer " + token;
                headers.put("Authorization", auth);
                return headers;
            }
        };
        queue.add(jsonObjectRequest);
    }
}


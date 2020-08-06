package com.example.dogify.Models;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.Playlist;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import retrofit.Callback;

@Parcel
public class MoodPlaylist {

    public String title;
    public Integer image;
    public String id;
    public List<PlaylistTrack> soundEffectTracks;
    public String playlist;


    public String getTitle(){
      return title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public Integer getImage(){
        return image;
    }

    public void setImage(Integer image){
        this.image = image;
    }

    public String getPlaylistId(){
        return id;
    }

    public void setPlaylistId(String id) {
        this.id = id;
    }

    public List<PlaylistTrack> getSoundEffectTracks(){
        return soundEffectTracks;
    }

    public void setSoundEffectTracks(List<PlaylistTrack> soundEffectTracks){
        this.soundEffectTracks = soundEffectTracks;
    }


    public String getPlaylist(){
        return playlist;
    }

    public void setPlaylist(String s, String playlist, Callback<Playlist> callback){
        this.playlist = playlist;
    }


}


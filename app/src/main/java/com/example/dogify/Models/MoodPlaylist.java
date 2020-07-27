package com.example.dogify.Models;

import org.parceler.Parcel;

@Parcel
public class MoodPlaylist {

    public String title;
    public Integer image;
    public String soundeffect;


    //for text
    public String getTitle(){
      return title;
    }

    public void setTitle(String t){
            title = t;
    }

    //for image
    public Integer getImage(){
        return image;
    }

    public void setImage(Integer i){
        image = i;
    }
}

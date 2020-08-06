package com.example.dogify.RVAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dogify.R;

import java.util.List;

import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.Track;
import retrofit.Callback;

public class SoundEffectAdapter extends RecyclerView.Adapter<SoundEffectAdapter.ViewHolder> {

    Context context;
    private List<PlaylistTrack> soundEffects;

    public SoundEffectAdapter(Context context, List<PlaylistTrack> soundEffects) {
        this.context = context;
        this.soundEffects = soundEffects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_soundeffect, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlaylistTrack soundEffect = soundEffects.get(position);
        holder.bind(soundEffect);
    }

    @Override
    public int getItemCount() {
      return soundEffects.size();
    }

    public void addAll(List<PlaylistTrack> soundEffects) {
        this.soundEffects.addAll(soundEffects);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDASoundEffect;
        ImageButton ibPlayButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDASoundEffect = itemView.findViewById(R.id.tvDASoundEffect);
            ibPlayButton = itemView.findViewById(R.id.ibPlayButton);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//                    String token = sharedPref.getString("token", "");
//
//                    int position = getAdapterPosition();
//                    MoodPlaylist moodPlaylist = moodPlaylists.get(position);
//                    Intent i = new Intent(context, DetailsActivity1.class);
//                    i.putExtra(MoodPlaylist.class.getSimpleName(), Parcels.wrap(moodPlaylist));
//                    i.putExtra("token", token);
//                    context.startActivity(i);
//
//                }
//            });
        }

        public void bind(PlaylistTrack soundEffect) {
            tvDASoundEffect.setText(soundEffect.track.name);
            Glide.with(context).load(R.mipmap.play_button).into(ibPlayButton);
        }
    }
}

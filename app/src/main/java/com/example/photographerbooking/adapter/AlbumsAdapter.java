package com.example.photographerbooking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photographerbooking.R;
import com.example.photographerbooking.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ItemViewHolder>{
    private List<Album> listAlbum = new ArrayList<>();

    public AlbumsAdapter() {
        setUpListAlbum();
    }

    private void setUpListAlbum() {
        this.listAlbum.add(new Album("Entertainments events at Melissa", R.drawable.melissa_1));
        this.listAlbum.add(new Album("Portrait photoshoot at Vienna", R.drawable.vienna_1));
        this.listAlbum.add(new Album("Love photoshoot at Vienna", R.drawable.vienna_2));
        this.listAlbum.add(new Album("Lifestyle photoshoot at Vienna", R.drawable.vienna_3));
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Album dto = listAlbum.get(position);
        holder.ivPhoto.setImageResource(dto.getRepresentativeImg());
        holder.tvDescription.setText(dto.getDescription());
    }

    @Override
    public int getItemCount() {
        return listAlbum.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView tvDescription;
        ImageView ivPhoto;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }
    }
}

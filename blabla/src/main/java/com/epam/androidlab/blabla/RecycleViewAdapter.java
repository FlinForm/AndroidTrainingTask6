package com.epam.androidlab.blabla;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemViewHolder> {

    private final List<ViewItems> viewItemList;

    public RecycleViewAdapter(List<ViewItems> viewItemList) {
        this.viewItemList = viewItemList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent
                .getContext())
                .inflate(R.layout.rview_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.band.setText(viewItemList.get(position).getBand());
        holder.coverName.setText(viewItemList.get(position).getAlbum());
        holder.coverImage.setImageResource(viewItemList.get(position).getAlbumCover());
    }

    @Override
    public int getItemCount() {
        return viewItemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        final TextView band;
        final TextView coverName;
        final ImageView coverImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            band = (TextView) itemView.findViewById(R.id.band);
            coverName = (TextView) itemView.findViewById(R.id.album_name);
            coverImage = (ImageView) itemView.findViewById(R.id.album_cover);
        }
    }
}

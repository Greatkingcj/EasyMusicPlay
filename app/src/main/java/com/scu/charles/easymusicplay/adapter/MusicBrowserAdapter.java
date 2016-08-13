package com.scu.charles.easymusicplay.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.scu.charles.easymusicplay.R;
import com.scu.charles.easymusicplay.model.Audio;

import java.util.List;

/**
 * Created by charles on 2016/8/9.
 */
public class MusicBrowserAdapter extends RecyclerView.Adapter<MusicBrowserAdapter.ViewHolder>{
    private List<Audio> mData;

    public MusicBrowserAdapter(List<Audio> data){
        mData= data;
    }

    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    public OnItemClickListener itemClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(
                R.layout.music_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.music_title.setText(mData.get(position).getTitle());
        holder.music_composer.setText(mData.get(position).getArtist());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView music_title;
        public TextView music_composer;
        public ViewHolder(View itemView) {
            super(itemView);
            music_title = (TextView) itemView.findViewById(R.id.music_title);
            music_composer = (TextView) itemView.findViewById(R.id.music_composer);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        itemClickListener.onItemClick(v,getPosition());
                    }
                }
            });
        }
    }
}

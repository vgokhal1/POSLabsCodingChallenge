package androidpractice.demo.com.poslabscodingchallenge;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;

public class MusicAlbumRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<MusicAlbum> mMusicList;
//    RecyclerAdapterActionListener recyclerAdapterActionListener;


    public MusicAlbumRecyclerAdapter(Context mainActivity, ArrayList<MusicAlbum> mMusicList) {
        this.ctx = mainActivity;
        this.layoutInflater = LayoutInflater.from(mainActivity);
        this.mMusicList = mMusicList;
//        this.recyclerAdapterActionListener = (RecyclerAdapterActionListener) mainActivity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;


        View view = layoutInflater.inflate(R.layout.music_album_recyclerview_element,parent,false);
        viewHolder = new MusicAlbumViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MusicAlbumViewHolder holder1 = (MusicAlbumViewHolder) holder;

        holder1.getTitle().setText(mMusicList.get(position).getmSongName());

        BackgroundImageLoader backgroundImageLoader = new BackgroundImageLoader(holder1.getPhoto(),mMusicList.get(position).getmAlbumPhotoURL());
        backgroundImageLoader.execute();

        holder1.getPhotoItemLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerAdapterActionListener.onItemSelected(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMusicList.size();
    }
}

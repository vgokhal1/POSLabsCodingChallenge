package androidpractice.demo.com.poslabscodingchallenge;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MusicAlbumViewHolder extends RecyclerView.ViewHolder {

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public TextView getTitle() {
        return title;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    ImageView photo;
    TextView title;
    RelativeLayout photoItemLayout;

    public RelativeLayout getPhotoItemLayout() {
        return photoItemLayout;
    }

    public void setPhotoItemLayout(RelativeLayout photoItemLayout) {
        this.photoItemLayout = photoItemLayout;
    }

    public MusicAlbumViewHolder(View itemView) {
        super(itemView);

        photo = (ImageView) itemView.findViewById(R.id.photo_album_imageview);
        title = (TextView) itemView.findViewById(R.id.music_album_title);
        photoItemLayout = (RelativeLayout) itemView.findViewById(R.id.music_item_layout);

    }
}

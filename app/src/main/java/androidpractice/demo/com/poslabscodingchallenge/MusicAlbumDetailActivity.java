package androidpractice.demo.com.poslabscodingchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MusicAlbumDetailActivity extends AppCompatActivity {

    TextView mArtistName,mAlbumName,mSongName,mSongPreviewURL,mCountry,mTrackPrice;
    ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_album_detail);

        MusicAlbum selected = (MusicAlbum) getIntent().getSerializableExtra("SelectedEmtry");

        photo = (ImageView) findViewById(R.id.music_album_imageview);
        mArtistName = (TextView) findViewById(R.id.artist_name);
        mAlbumName = (TextView) findViewById(R.id.album_name);
        mSongName = (TextView) findViewById(R.id.song_name);
        mSongPreviewURL = (TextView) findViewById(R.id.song_preview_URL);
        mCountry = (TextView) findViewById(R.id.country);
        mTrackPrice = (TextView) findViewById(R.id.track_price);

        BackgroundImageLoader backgroundImageLoader = new BackgroundImageLoader(photo,selected.getmAlbumPhotoURL());
        backgroundImageLoader.execute();


        if (!TextUtils.isEmpty(selected.getmArtistName())){
            mArtistName.setText("Artist: "+selected.getmArtistName());
        }

        if (!TextUtils.isEmpty(selected.getmAlbumName())){
            mAlbumName.setText("Album: "+selected.getmAlbumName());
        }

        if (!TextUtils.isEmpty(selected.getmSongName())){
            mSongName.setText("Song: "+selected.getmSongName());
        }

        if (!TextUtils.isEmpty(selected.getmSongPreviewURL())){
            mSongPreviewURL.setText("Preview URL: "+selected.getmSongPreviewURL());
        }

        if (!TextUtils.isEmpty((String) selected.getmCountry())){
            mCountry.setText("Country: "+selected.getmCountry());
        }

        if (!TextUtils.isEmpty(selected.getmTrackPrice()+selected.getmCurrency())){
            mTrackPrice.setText("Track Price: "+selected.getmTrackPrice()+" "+selected.getmCurrency());
        }
    }
}

package androidpractice.demo.com.poslabscodingchallenge;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,JSONFetchListener,RecyclerAdapterActionListener {

    SwipeRefreshLayout mSwipeRefreshLayout = null;
    RecyclerView mMusicAlbumRecyclerView;
    ArrayList<MusicAlbum> mMusicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initiateViews();
        fetchJSONData();
    }

    private void initiateViews(){

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.homescreen);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        mMusicAlbumRecyclerView = (RecyclerView) findViewById(R.id.music_album_recyclerview);

        mMusicAlbumRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void fetchJSONData() {
        mSwipeRefreshLayout.setRefreshing(true);
        JSONBackgroundFetch jsonBackgroundFetch = new JSONBackgroundFetch(this);
        jsonBackgroundFetch.execute();
    }

    @Override
    public void onRefresh() {
        fetchJSONData();
    }

    @Override
    public void processFinish(String response) {
//        Log.e("JSON","Response: "+response);

        JSONArray jsonArray;
        mMusicList = new ArrayList<>();

        try {

            JSONObject jsonObject = new JSONObject(response);

            jsonArray = jsonObject.getJSONArray("results");

            if (jsonArray.length()>0){

                MusicAlbum musicAlbum;
                JSONObject temp;

                for (int i=0;i<jsonArray.length();i++){

                    musicAlbum = new MusicAlbum();
                    temp = (JSONObject) jsonArray.get(i);

                    if (temp.has("artistName") && !TextUtils.isEmpty(temp.getString("artistName"))){
                        musicAlbum.setmArtistName(temp.getString("artistName"));
                    }

                    if (temp.has("collectionCensoredName") && !TextUtils.isEmpty(temp.getString("collectionCensoredName"))){
                        musicAlbum.setmAlbumName(temp.getString("collectionCensoredName"));
                    }

                    if (temp.has("trackCensoredName") && !TextUtils.isEmpty(temp.getString("trackCensoredName"))){
                        musicAlbum.setmSongName(temp.getString("trackCensoredName"));
                    }

                    if (temp.has("previewUrl") && !TextUtils.isEmpty(temp.getString("previewUrl"))){
                        musicAlbum.setmSongPreviewURL(temp.getString("previewUrl"));
                    }

                    if (temp.has("artworkUrl100") && !TextUtils.isEmpty(temp.getString("artworkUrl100"))){
                        musicAlbum.setmAlbumPhotoURL(temp.getString("artworkUrl100"));
                    }

                    if (temp.has("country") && !TextUtils.isEmpty(temp.getString("country"))){
                        musicAlbum.setmCountry(temp.getString("country"));
                    }

                    if (temp.has("trackPrice") && temp.getDouble("trackPrice")>0){
                        musicAlbum.setmTrackPrice(temp.getDouble("trackPrice"));
                    }

                    if (temp.has("currency") && !TextUtils.isEmpty(temp.getString("currency"))){
                        musicAlbum.setmCurrency(temp.getString("currency"));
                    }

                    mMusicList.add(musicAlbum);

                }


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Bind the data to the Recyclerview...
        MusicAlbumRecyclerAdapter musicAlbumRecyclerAdapter= new MusicAlbumRecyclerAdapter(MainActivity.this, mMusicList);
        mMusicAlbumRecyclerView.setAdapter(musicAlbumRecyclerAdapter);

        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemSelected(int position) {

        MusicAlbum selected = mMusicList.get(position);

        Intent intent = new Intent(this, MusicAlbumDetailActivity.class);
        intent.putExtra("SelectedEmtry",selected);
        startActivity(intent);

    }
}

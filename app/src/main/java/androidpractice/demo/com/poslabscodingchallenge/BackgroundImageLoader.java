package androidpractice.demo.com.poslabscodingchallenge;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class BackgroundImageLoader extends AsyncTask<Void, Void, Bitmap> {

    ImageView imageView;
    String urlPath;
    final static int mImgViewDimentions = 250;

    public BackgroundImageLoader(ImageView img,String url){
        this.imageView = img;
        this.urlPath = url;
    }

    @Override
    protected void onPreExecute() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
        params.width = mImgViewDimentions;
        params.height = mImgViewDimentions;

        imageView.setLayoutParams(params);
    }

    @Override
    protected Bitmap doInBackground(Void... strings) {

        Bitmap bmp = null;
        try {
            URL myUrl = new URL(urlPath);
            InputStream in = myUrl.openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap,mImgViewDimentions,mImgViewDimentions,false));
        imageView.setColorFilter(null);
    }
}

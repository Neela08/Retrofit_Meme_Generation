package com.bitwisey.a10gag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
ProgressDialog progressDialog;ImageView img;RetroInfo retrofit;String theUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theUrl="";
        img=(ImageView)findViewById(R.id.imageView) ;
        progressDialog = new ProgressDialog(MainActivity.this);
       progressDialog.setMessage("Loading....");
        progressDialog.show();
        loadMeme();

    }

    public void next(View view) {
        progressDialog.show();
        loadMeme();
    }

    public void share(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType( "text/plain");
        i.putExtra(Intent.EXTRA_TEXT, "Hi, checkout this meme "+theUrl);
        startActivity(Intent.createChooser(i, "Share this meme with"));
    }
    public void loadMeme(){
        Call<RetroInfo> call=Client.getInstance().getMyApi().getMemes();

        call.enqueue(new Callback<RetroInfo>() {
            @Override
            public void onResponse(Call<RetroInfo> call, Response<RetroInfo> response) {

                RetroInfo retrofit=response.body();
                theUrl=retrofit.getUrl();
                Picasso.Builder builder = new Picasso.Builder(MainActivity.this);
                builder.downloader(new OkHttp3Downloader(MainActivity.this));
                builder.build().load(retrofit.getUrl())
                        .placeholder((R.color.white))
                        .error(R.color.white)
                        .into(img);

                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<RetroInfo> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }
}

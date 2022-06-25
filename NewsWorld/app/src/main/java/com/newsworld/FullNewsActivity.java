package com.newsworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class FullNewsActivity extends AppCompatActivity {

    TextView textView, dsc, time, channel;
    ImageView imageView;
    Button ReadMore;
    String Url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);
        textView = findViewById(R.id.textView1);
        time = findViewById(R.id.time);
        channel = findViewById(R.id.channel);
        dsc = findViewById(R.id.Description);
        imageView = findViewById(R.id.imageView1);
        ReadMore = findViewById(R.id.ReadMore);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Intent intent = getIntent();
        String Title = intent.getStringExtra("Title");
        String Description = intent.getStringExtra("Description");
        String Image = intent.getStringExtra("Image");
        String Content = intent.getStringExtra("Content");
        String Channel = intent.getStringExtra("Channel");
        String Time = intent.getStringExtra("Time");
        Url = intent.getStringExtra("Url");

        textView.setText(Title);
        dsc.setText(Description);
        channel.setText(Channel);
        time.setText(Time);
        Glide.with(this).load(Image).into(imageView);
        ReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView myWebView = new WebView(FullNewsActivity.this);
                setContentView(myWebView);
                myWebView.loadUrl(Url);
            }
        });

        Log.d("Main","create11111");



    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main","Resume11111");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Main","Restart1111");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main","Pause1111");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main","destroy1111");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main","Start1111");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main","Stop1111");
    }

}
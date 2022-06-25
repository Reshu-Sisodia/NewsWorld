package com.newsworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<HeadLine> list, String message) {
            ShowNews(list);

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Message"+message, Toast.LENGTH_SHORT).show();

        }
    };
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.Logout);
        progressBar = findViewById(R.id.progress);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Log.d("Main","Create");

        textView.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent  intent = new Intent(MainActivity.this,SignInActivity.class);
            startActivity(intent);
            finish();
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getHeadlines(listener, "general", "");
            }
        }).start();



    }

    private void ShowNews(List<HeadLine> list) {
        recyclerView = findViewById(R.id.RecyclerNews);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, list);
        recyclerView.setAdapter(newsAdapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Main","Restart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main","Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main","destroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Main","Start");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Main","Stop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main","Resume");
    }

}
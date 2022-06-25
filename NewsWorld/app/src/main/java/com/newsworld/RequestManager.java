package com.newsworld;

import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder().
            baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getHeadlines(OnFetchDataListener listener, String category, String query) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callHeadline("in", category, query, "96fd5a0d82194423b6d572deaed7d0ca");
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<NewsApiResponse> call, @NonNull Response<NewsApiResponse> response) {

                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
                    }
                    assert response.body() != null;
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(@NonNull Call<NewsApiResponse> call, Throwable t) {
                    Toast.makeText(context, "Server not Responding", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public interface CallNewsApi {
        @GET("top-headlines")
        Call<NewsApiResponse> callHeadline(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String api_key



        );
    }
}

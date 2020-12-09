package com.moringaschool.mobilelibrary.network;

import com.moringaschool.mobilelibrary.model.GoogleBooksSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApi {
    @GET("volumes")
    Call<GoogleBooksSearchResponse> getBooks(
            @Query("q")String q,
            @Query("key") String key

    );

}


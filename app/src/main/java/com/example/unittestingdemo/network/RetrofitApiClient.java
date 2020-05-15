package com.example.unittestingdemo.network;

import com.example.unittestingdemo.model.User;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApiClient {
    @GET("/users/{id}")
    Flowable<User> getUser(@Path("id") int id);
}

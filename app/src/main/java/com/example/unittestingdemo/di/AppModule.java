package com.example.unittestingdemo.di;

import com.example.unittestingdemo.network.RetrofitApiClient;
import com.example.unittestingdemo.repository.RemoteRepository;
import com.example.unittestingdemo.repository.RemoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static RetrofitApiClient provideApiClient() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitApiClient.class);
    }

    @Singleton
    @Provides
    static RemoteRepository provideRemoteRepository(RetrofitApiClient retrofitApiClient) {
        return new RemoteRepositoryImpl(retrofitApiClient);
    }
}

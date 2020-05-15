package com.example.unittestingdemo.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;

import com.example.unittestingdemo.model.User;
import com.example.unittestingdemo.network.RetrofitApiClient;

public class RemoteRepositoryImpl implements RemoteRepository {
    private final RetrofitApiClient apiClient;
    private final MediatorLiveData<User> userMediatorLiveData = new MediatorLiveData<>();

    public RemoteRepositoryImpl(RetrofitApiClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public LiveData<User> getUser(int id) {

        LiveData<User> source = LiveDataReactiveStreams
                .fromPublisher(apiClient.getUser(id)
                        .onErrorReturn(throwable -> new User(-1, null, null, null)));

        userMediatorLiveData.addSource(source, user -> {
            userMediatorLiveData.setValue(user);
            userMediatorLiveData.removeSource(source);
        });

        return userMediatorLiveData;
    }
}

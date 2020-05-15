package com.example.unittestingdemo.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.unittestingdemo.model.User;
import com.example.unittestingdemo.repository.RemoteRepository;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    private final RemoteRepository remoteRepository;

    @Inject
    public MainActivityViewModel(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public LiveData<User> getValue() {
        return remoteRepository.getUser(1);
    }
}

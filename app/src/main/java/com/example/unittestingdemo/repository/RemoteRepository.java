package com.example.unittestingdemo.repository;

import androidx.lifecycle.LiveData;

import com.example.unittestingdemo.model.User;

public interface RemoteRepository {
    LiveData<User> getUser(int id);
}

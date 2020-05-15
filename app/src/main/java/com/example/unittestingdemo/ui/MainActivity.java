package com.example.unittestingdemo.ui;

import android.os.Bundle;

import androidx.lifecycle.ViewModelProvider;

import com.example.unittestingdemo.databinding.ActivityMainBinding;
import com.example.unittestingdemo.di.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private static final String TAG = "sayed";
    @Inject
    ViewModelProviderFactory providerFactory;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this, providerFactory).get(MainActivityViewModel.class);

        binding.button.setOnClickListener(click -> {
            subscribeObserver();
        });
    }

    private void subscribeObserver() {
        viewModel.getValue().observe(this, user -> {
        });
    }

}

package com.example.unittestingdemo.di;

import androidx.lifecycle.ViewModel;

import com.example.unittestingdemo.ui.MainActivityViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelBuilders {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    public abstract ViewModel bindMainActivityViewModel(MainActivityViewModel mainActivityViewModel);
}

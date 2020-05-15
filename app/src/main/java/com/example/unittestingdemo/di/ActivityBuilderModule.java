package com.example.unittestingdemo.di;

import com.example.unittestingdemo.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
            modules = {
                    ViewModelBuilders.class
            }
    )
    abstract MainActivity mainActivity();
}

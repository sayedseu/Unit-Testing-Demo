package com.example.unittestingdemo.repository;

import android.content.SharedPreferences;

import com.example.unittestingdemo.model.SharedPreferenceEntry;

import java.util.Calendar;

public class SharedPreferencesHelper {

    static final String KEY_NAME = "key_name";
    static final String KEY_DOB = "key_dob_millis";
    static final String KEY_EMAIL = "key_email";

    private final SharedPreferences mSharedPreferences;

    SharedPreferencesHelper(SharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }


    boolean savePersonalInfo(SharedPreferenceEntry sharedPreferenceEntry) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(KEY_NAME, sharedPreferenceEntry.getName());
        editor.putLong(KEY_DOB, sharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.getEmail());
        return editor.commit();
    }


    SharedPreferenceEntry getPersonalInfo() {
        String name = mSharedPreferences.getString(KEY_NAME, "");
        long dobMillis = mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().getTimeInMillis());
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTimeInMillis(dobMillis);
        String email = mSharedPreferences.getString(KEY_EMAIL, "");
        return new SharedPreferenceEntry(name, dateOfBirth, email);
    }
}

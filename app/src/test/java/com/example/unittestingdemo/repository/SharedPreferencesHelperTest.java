package com.example.unittestingdemo.repository;

import android.content.SharedPreferences;

import com.example.unittestingdemo.model.SharedPreferenceEntry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharedPreferencesHelperTest {

    private static final String TEST_NAME = "Test name";
    private static final String TEST_EMAIL = "test@email.com";
    private static final Calendar TEST_DATE_OF_BIRTH = Calendar.getInstance();

    static {
        TEST_DATE_OF_BIRTH.set(1980, 1, 1);
    }

    @Mock
    SharedPreferences mMockSharedPreferences;

    @Mock
    SharedPreferences mMockBrokenSharedPreferences;

    @Mock
    SharedPreferences.Editor mMockEditor;

    @Mock
    SharedPreferences.Editor mMockBrokenEditor;

    private SharedPreferenceEntry mSharedPreferenceEntry;
    private SharedPreferencesHelper mMockSharedPreferencesHelper;
    private SharedPreferencesHelper mMockBrokenSharedPreferencesHelper;

    @Before
    public void setUp() throws Exception {
        mSharedPreferenceEntry = new SharedPreferenceEntry(TEST_NAME, TEST_DATE_OF_BIRTH, TEST_EMAIL);
        mMockSharedPreferencesHelper = createMockSharedPreference();
        mMockBrokenSharedPreferencesHelper = createBrokenMockSharedPreference();
    }


    @Test
    public void sharedPreferencesHelper_SaveAndReadPersonalInformation() {
        boolean success = mMockSharedPreferencesHelper.savePersonalInfo(mSharedPreferenceEntry);
        assertThat("Checking that SharedPreferenceEntry.save... returns true", success, is(true));
        SharedPreferenceEntry savedSharedPreferenceEntry = mMockSharedPreferencesHelper.getPersonalInfo();

        System.out.println(savedSharedPreferenceEntry.getName());

        assertThat("Checking that SharedPreferenceEntry.name has been persisted and read correctly",
                mSharedPreferenceEntry.getName(),
                is(equalTo(savedSharedPreferenceEntry.getName())));
        assertThat("Checking that SharedPreferenceEntry.dateOfBirth has been persisted and read "
                        + "correctly",
                mSharedPreferenceEntry.getDateOfBirth(),
                is(equalTo(savedSharedPreferenceEntry.getDateOfBirth())));
        assertThat("Checking that SharedPreferenceEntry.email has been persisted and read "
                        + "correctly",
                mSharedPreferenceEntry.getEmail(),
                is(equalTo(savedSharedPreferenceEntry.getEmail())));
    }


    @Test
    public void sharedPreferencesHelper_SavePersonalInformationFailed_ReturnsFalse() {
        boolean success = mMockBrokenSharedPreferencesHelper.savePersonalInfo(mSharedPreferenceEntry);
        assertThat("Makes sure writing to a broken SharedPreferencesHelper returns false", success,
                is(false));
    }

    private SharedPreferencesHelper createMockSharedPreference() {
        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_NAME), anyString()))
                .thenReturn(mSharedPreferenceEntry.getName());
        when(mMockSharedPreferences.getString(eq(SharedPreferencesHelper.KEY_EMAIL), anyString()))
                .thenReturn(mSharedPreferenceEntry.getEmail());
        when(mMockSharedPreferences.getLong(eq(SharedPreferencesHelper.KEY_DOB), anyLong()))
                .thenReturn(mSharedPreferenceEntry.getDateOfBirth().getTimeInMillis());
        when(mMockEditor.commit()).thenReturn(true);
        when(mMockSharedPreferences.edit()).thenReturn(mMockEditor);
        return new SharedPreferencesHelper(mMockSharedPreferences);
    }


    private SharedPreferencesHelper createBrokenMockSharedPreference() {
        when(mMockBrokenEditor.commit()).thenReturn(false);
        when(mMockBrokenSharedPreferences.edit()).thenReturn(mMockBrokenEditor);
        return new SharedPreferencesHelper(mMockBrokenSharedPreferences);
    }
}
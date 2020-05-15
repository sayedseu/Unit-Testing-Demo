package com.example.unittestingdemo.repository;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.example.unittestingdemo.model.User;
import com.example.unittestingdemo.network.RetrofitApiClient;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoteRepositoryTest {

    private final User testingUser = new User(1,
            "Sayed Hasan",
            "sayed",
            "sayed@gmail.com");
    @Rule
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();
    @Captor
    ArgumentCaptor<Integer> acInteger;
    private RemoteRepository repository;
    @Mock
    private RetrofitApiClient apiClient;
    @Mock
    private Observer<User> mockObserver;

    @Before
    public void setUp() {
        repository = new RemoteRepositoryImpl(apiClient);
    }

    @Test
    public void fetchSuccessUserData() {
        when(apiClient.getUser(anyInt())).thenReturn(Flowable.just(testingUser));
        repository.getUser(1).observeForever(mockObserver);
        assertThat("testing with no error", repository.getUser(1).getValue().getId(), is(equalTo(testingUser.getId())));

        verify(apiClient, times(2)).getUser(acInteger.capture());
        assertEquals(2, acInteger.getAllValues().size());

        InOrder order = Mockito.inOrder(apiClient);
        order.verify(apiClient, times(2)).getUser(ArgumentMatchers.any(Integer.class));
        verifyZeroInteractions(apiClient);
    }

    @Test
    public void fetchErrorUserData() {
        when(apiClient.getUser(anyInt())).thenReturn(Flowable.error(new Exception()));
        repository.getUser(1).observeForever(mockObserver);
        assertThat("testing with error", repository.getUser(1).getValue().getId(), is(equalTo(-1)));

        verify(apiClient, times(2)).getUser(acInteger.capture());
        assertEquals(2, acInteger.getAllValues().size());

        InOrder order = Mockito.inOrder(apiClient);
        order.verify(apiClient, times(2)).getUser(ArgumentMatchers.any(Integer.class));
        verifyZeroInteractions(apiClient);

    }
}
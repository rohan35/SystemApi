package com.raydevelopers.moengage.repositories;

import androidx.lifecycle.MutableLiveData;

import com.raydevelopers.moengage.network.HttpRequestTask;
import com.raydevelopers.moengage.network.NetworkResource;
import com.raydevelopers.moengage.network.ProcessNetworkResponse;

public class AppRepository<T> {

    public MutableLiveData<NetworkResource<T>> getNews(String url, Class<T> objectClass) {
        MutableLiveData<NetworkResource<T>> response = new MutableLiveData<>();
        ProcessNetworkResponse networkResponse = new ProcessNetworkResponse(response, objectClass);
        new HttpRequestTask(networkResponse).execute(url);
        return response;
    }
}

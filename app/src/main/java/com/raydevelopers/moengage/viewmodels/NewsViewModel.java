package com.raydevelopers.moengage.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.raydevelopers.moengage.data.News;
import com.raydevelopers.moengage.network.NetworkResource;

public class NewsViewModel extends ViewModel {
    private NewsResponseProcessor responseProcessor;
    public MutableLiveData<News> newLiveData = new MutableLiveData<>();

    public NewsViewModel(NewsResponseProcessor responseProcessor) {
        this.responseProcessor = responseProcessor;
    }

    public LiveData<NetworkResource> fetchNews() {
        return responseProcessor.fetchNews();
    }
}

package com.raydevelopers.moengage.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.raydevelopers.moengage.data.ApplicationConstants;
import com.raydevelopers.moengage.data.News;
import com.raydevelopers.moengage.repositories.AppRepository;

public class NewsViewModel extends ViewModel {
    private NewsResponseProcessor responseProcessor;
    public NewsViewModel(NewsResponseProcessor responseProcessor) {
        this.responseProcessor = responseProcessor;
    }

    public LiveData<News> fetchNews()
    {
       return (LiveData<News>) responseProcessor.getNews(ApplicationConstants.url,News.class);
    }
}

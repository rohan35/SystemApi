package com.raydevelopers.moengage.viewmodels;

import android.content.ContentValues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.raydevelopers.moengage.data.ApplicationConstants;
import com.raydevelopers.moengage.data.Article;
import com.raydevelopers.moengage.data.News;
import com.raydevelopers.moengage.database.NewsDbOperationHelper;
import com.raydevelopers.moengage.database.NewsReaderContract;
import com.raydevelopers.moengage.enums.Status;
import com.raydevelopers.moengage.network.NetworkResource;
import com.raydevelopers.moengage.repositories.AppRepository;

public class NewsResponseProcessor {
    private AppRepository mRepository;
    private NewsDbOperationHelper dbOperationHelper;

    public NewsResponseProcessor(AppRepository repository, NewsDbOperationHelper dbOperationHelper) {
        this.mRepository = repository;
        this.dbOperationHelper = dbOperationHelper;
    }

    public LiveData<NetworkResource> fetchNews()
    {
        return mRepository.getNews(ApplicationConstants.url, News.class);
    }
    public void getNews() {
        mRepository.getNews(ApplicationConstants.url, News.class)
                .observeForever(new Observer() {
                    @Override
                    public void onChanged(Object res) {
                        // trigger database operation
                        NetworkResource<News> response = getNetworkResource(res);
                        processNewsResponse(response);
                    }
                });
    }

    private NetworkResource<News> getNetworkResource(Object response) {
        if (response instanceof NetworkResource) {
            return (NetworkResource<News>) response;
        }
        return null;
    }

    private void processNewsResponse(NetworkResource<News> response) {

        if (response != null && response.status == Status.SUCCESS) {
            // recieved sucessfull response
            // add data int database
            if (response.data != null) {
                deleteDataFromDatabase();
                insertDataIntoDatabase(response.data);
            }
        } else {
            // return error case to the caller
        }
    }

    private void deleteDataFromDatabase() {
        dbOperationHelper.delete(
                NewsReaderContract.NewsEntry.CONTENT_URI,
                null,
                null);
    }

    private void insertDataIntoDatabase(News data) {
        ContentValues[] values = new ContentValues[data.getArticles().size()];
        int i = 0;
        for (Article article : data.getArticles()) {
            ContentValues newsValues = new ContentValues();
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_AUTHOR, article.getAuthor());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_TITLE, article.getTitle());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_DESCRIPTION, article.getDescription());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_URL, article.getUrl());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE, article.getUrlToImage());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT, article.getPublishedAt());
            newsValues.put(NewsReaderContract.NewsEntry.COLUMN_NAME_CONTENT, article.getContent());
            values[i] = newsValues;
            i++;
        }
        if (values != null && values.length != 0) {
            // Get a handle on the ContentResolver to insert data
            dbOperationHelper.bulkInsert(
                    NewsReaderContract.NewsEntry.CONTENT_URI,
                    values
            );
        }

    }
}

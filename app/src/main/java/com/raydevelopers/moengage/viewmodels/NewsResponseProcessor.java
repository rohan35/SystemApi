package com.raydevelopers.moengage.viewmodels;

import android.content.ContentValues;

import androidx.lifecycle.Observer;

import com.raydevelopers.moengage.data.ApplicationConstants;
import com.raydevelopers.moengage.data.Article;
import com.raydevelopers.moengage.data.News;
import com.raydevelopers.moengage.database.NewsReaderContract;
import com.raydevelopers.moengage.enums.Status;
import com.raydevelopers.moengage.network.NetworkResource;
import com.raydevelopers.moengage.repositories.AppRepository;

public class NewsResponseProcessor {
    private AppRepository repository;

    public NewsResponseProcessor(AppRepository repository) {
        this.repository = repository;
    }

    public void getNews() {
        repository.getNews(ApplicationConstants.url, News.class)
                .observeForever(new Observer() {
                    @Override
                    public void onChanged(Object res) {
                        // trigger database operation
                        NetworkResource<News> response = getNetworkResource(res);
                        generateNewsSchema(response);
                    }
                });
    }

    public NetworkResource<News> getNetworkResource(Object response) {
        if (response instanceof NetworkResource) {
            return (NetworkResource<News>) response;
        }
        return null;
    }

    public void generateNewsSchema(NetworkResource<News> response) {
        if (response == null) {
            // do whatever you want to do
        }
        if (response.status == Status.SUCCESS) {
            // recieved sucessfull response
            // add data int database
            if (response.data != null) {
                insertDataIntoDatabase(response.data);
            }
        } else {

        }
    }

    public void insertDataIntoDatabase(News data) {
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
            /* Get a handle on the ContentResolver to delete and insert data */

        }

    }
}

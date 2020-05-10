package com.raydevelopers.moengage.services;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.raydevelopers.moengage.data.ApplicationConstants;
import com.raydevelopers.moengage.data.Article;
import com.raydevelopers.moengage.data.News;
import com.raydevelopers.moengage.database.NewsReaderContract;
import com.raydevelopers.moengage.dependencyInjector.DependencyProvider;
import com.raydevelopers.moengage.enums.Status;
import com.raydevelopers.moengage.network.NetworkResource;

public class NewsRetrievalService extends IntentService {
    public NewsRetrievalService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }
}

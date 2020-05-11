package com.raydevelopers.moengage.dependencyInjector;

import com.raydevelopers.moengage.NewsApplication;
import com.raydevelopers.moengage.database.NewsDbOperationHelper;
import com.raydevelopers.moengage.database.NewsReaderDbHelper;
import com.raydevelopers.moengage.factories.NewsViewModelProviderFactory;
import com.raydevelopers.moengage.repositories.AppRepository;
import com.raydevelopers.moengage.viewmodels.NewsResponseProcessor;

public class DependencyProvider {
    // static variable single_instance of type Singleton
    private static DependencyProvider single_instance = null;


    // static method to create instance of Singleton class
    public static DependencyProvider getInstance() {
        if (single_instance == null)
            single_instance = new DependencyProvider();

        return single_instance;
    }

    public NewsViewModelProviderFactory provideNewsViewModel()
    {
        return new NewsViewModelProviderFactory(getNewsResponseProcessor());
    }
    public NewsResponseProcessor getNewsResponseProcessor()
    {
        return new NewsResponseProcessor(getAppRepository(),getNewsDbOperationHelper());
    }
    private NewsDbOperationHelper getNewsDbOperationHelper()
    {
       return new NewsDbOperationHelper(getNewsDbHelper());
    }
    private NewsReaderDbHelper getNewsDbHelper()
    {
        return new NewsReaderDbHelper(NewsApplication.getAppContext());
    }
    private AppRepository getAppRepository()
    {
        return new AppRepository();
    }

}

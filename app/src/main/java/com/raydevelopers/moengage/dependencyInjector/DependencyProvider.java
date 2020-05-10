package com.raydevelopers.moengage.dependencyInjector;

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
        return new NewsResponseProcessor(getAppRepository());
    }
    public AppRepository getAppRepository()
    {
        return new AppRepository();
    }
}

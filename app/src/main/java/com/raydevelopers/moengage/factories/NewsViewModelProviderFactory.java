package com.raydevelopers.moengage.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.raydevelopers.moengage.repositories.AppRepository;
import com.raydevelopers.moengage.viewmodels.NewsResponseProcessor;
import com.raydevelopers.moengage.viewmodels.NewsViewModel;

public class NewsViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {
    private NewsResponseProcessor responseProcessor;
    public NewsViewModelProviderFactory(NewsResponseProcessor responseProcessor) {
        this.responseProcessor = responseProcessor;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NewsViewModel.class)) {
            return (T) new NewsViewModel(responseProcessor);
        }
        throw new ClassCastException("Class cannot be casted to the specified class");

    }
}

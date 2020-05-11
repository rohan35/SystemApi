package com.raydevelopers.moengage.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.raydevelopers.moengage.BR;
import com.raydevelopers.moengage.R;
import com.raydevelopers.moengage.databinding.ActivityNewsBinding;
import com.raydevelopers.moengage.dependencyInjector.DependencyProvider;
import com.raydevelopers.moengage.viewmodels.NewsViewModel;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityNewsBinding binding = DataBindingUtil.setContentView(this,
                R.layout.activity_news);
        binding.setVariable(BR.newsViewModel, getViewModel());
        binding.executePendingBindings();
        setSupportActionBar(binding.toolBar);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private NewsViewModel getViewModel() {
        return new ViewModelProvider(this, DependencyProvider.getInstance().provideNewsViewModel())
                .get(NewsViewModel.class);
    }
}

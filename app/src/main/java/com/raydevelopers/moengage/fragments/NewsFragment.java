package com.raydevelopers.moengage.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.raydevelopers.moengage.R;
import com.raydevelopers.moengage.adapter.NewsRecyclerAdapter;
import com.raydevelopers.moengage.data.Article;
import com.raydevelopers.moengage.databinding.FragmentNewsBinding;
import com.raydevelopers.moengage.dependencyInjector.DependencyProvider;
import com.raydevelopers.moengage.enums.Status;
import com.raydevelopers.moengage.network.NetworkResource;
import com.raydevelopers.moengage.viewmodels.NewsViewModel;

import java.util.ArrayList;

public class NewsFragment extends Fragment {

    private FragmentNewsBinding mViewDataBinding;
    private NewsRecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false);
        return mViewDataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // make network request
        getViewModel().fetchNews().observe(getViewLifecycleOwner(), new Observer<NetworkResource>() {
            @Override
            public void onChanged(NetworkResource networkResource) {
                if (networkResource.status == Status.SUCCESS) {
                    System.out.println("Ho we are in");
                } else {
                    System.out.println("Ho we are not in");
                }
            }
        });
    }

    private void setUpRecyclerView(ArrayList<Article> list)
    {
        mAdapter = new NewsRecyclerAdapter(list);
        mViewDataBinding.recyclerView.setAdapter(mAdapter);

    }

    private NewsViewModel getViewModel() {
        return new ViewModelProvider(this, DependencyProvider.getInstance().provideNewsViewModel())
                .get(NewsViewModel.class);
    }
}


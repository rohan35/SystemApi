package com.raydevelopers.moengage.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.raydevelopers.moengage.BR;
import com.raydevelopers.moengage.R;
import com.raydevelopers.moengage.data.Article;
import com.raydevelopers.moengage.databinding.LayoutNewsItemBinding;

import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Article> list = new ArrayList<>();

    public NewsRecyclerAdapter(ArrayList<Article> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutNewsItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.layout_news_item, parent, false);

        return new NewsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NewsViewHolder) holder).bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public LayoutNewsItemBinding itemRowBinding;

        public NewsViewHolder(LayoutNewsItemBinding itemRowBinding) {
            super(itemRowBinding.getRoot());
            this.itemRowBinding = itemRowBinding;
        }

        public void bind(Object obj) {
            itemRowBinding.setVariable(BR.articleModel, obj);
            itemRowBinding.executePendingBindings();
        }
    }
}

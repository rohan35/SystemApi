package com.raydevelopers.moengage.utiltiy;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

class LiveDataUtils {
    public static <T> void observeOnce(final LiveData<T> liveData, final Observer<T> observer) {
        liveData.observeForever(new Observer<T>() {
            @Override
            public void onChanged(T t) {
                observer.onChanged(t);
                liveData.removeObserver(this);
            }
        });
    }
}
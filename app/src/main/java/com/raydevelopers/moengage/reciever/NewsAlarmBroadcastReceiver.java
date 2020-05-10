package com.raydevelopers.moengage.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.raydevelopers.moengage.services.NewsRetrievalService;

public class NewsAlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // trigger the serice to  sync the data
        Intent i = new Intent(context, NewsRetrievalService.class);
        context.startService(i);
    }
}

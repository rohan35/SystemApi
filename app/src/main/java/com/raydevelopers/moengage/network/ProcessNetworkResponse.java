package com.raydevelopers.moengage.network;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.raydevelopers.moengage.enums.Status;
import com.raydevelopers.moengage.interfaces.OnNetworkResponse;

public class ProcessNetworkResponse<T> implements OnNetworkResponse {
    private MutableLiveData<NetworkResource<T>> responseData;
    private Class<T> objectClass;

    public ProcessNetworkResponse(MutableLiveData<NetworkResource<T>> response, Class<T> objectClass) {
        this.responseData = response;
        this.objectClass = objectClass;
    }

    @Override
    public void onResultReceived(HttpResponseObject response) {
        if (response != null && response.getStatus() == Status.SUCCESS && !TextUtils.isEmpty(response.getResponseBody())) {
            // success post the value to the main thread
            responseData.postValue(NetworkResource.success(
                    getModelFromJsonString(response.getResponseBody(), objectClass), response.getStatusCode()));
        } else {
            // error post the value to main thread
            responseData.postValue(NetworkResource.error(
                    getModelFromJsonString(response.getResponseBody(), objectClass), response.getError(), response.getStatusCode()));
        }

    }

    public <T> T getModelFromJsonString(@NonNull String response, @NonNull Class<T> modelClass) {
        try {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(response, modelClass);
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }

}

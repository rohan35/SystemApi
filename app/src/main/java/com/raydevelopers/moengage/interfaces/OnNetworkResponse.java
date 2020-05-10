package com.raydevelopers.moengage.interfaces;

import com.raydevelopers.moengage.network.HttpResponseObject;

import org.json.JSONObject;

public interface OnNetworkResponse {
    void onResultReceived(HttpResponseObject response);
}

package com.raydevelopers.moengage.network;

import android.os.AsyncTask;

import com.raydevelopers.moengage.interfaces.OnNetworkResponse;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequestTask extends AsyncTask<String, Integer, HttpResponseObject> {
    private OnNetworkResponse mListener;

    public HttpRequestTask(OnNetworkResponse listener) {
        this.mListener = listener;
    }

    @Override
    protected HttpResponseObject doInBackground(String... params) {
        String providedUrl = params[0];
        StringBuilder result = new StringBuilder();
        HttpResponseObject responseObject = null;
        try {
            URL url = new URL(providedUrl);
            // opeing a connection
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // setting the  Request Method Type
            httpURLConnection.setRequestMethod("GET");
            // adding the headers for request
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            try {
                // initialize the reader for getting the input stream
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(httpURLConnection.getResponseCode() == 200 ?
                                httpURLConnection.getInputStream() : httpURLConnection.getErrorStream()));
                // read the result from stream
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                if(httpURLConnection.getResponseCode() == 200)
                {
                    // success case
                    responseObject = new HttpResponseObject(httpURLConnection.getResponseCode(),
                            result.toString(), com.raydevelopers.moengage.enums.Status.SUCCESS,null);
                }
                else
                {
                    // Error Occurred
                    responseObject = new HttpResponseObject(httpURLConnection.getResponseCode(),
                            result.toString(), com.raydevelopers.moengage.enums.Status.ERROR,null);
                }

            } catch (Exception e) {
                responseObject = new HttpResponseObject(httpURLConnection.getResponseCode(),
                        e.getMessage(), com.raydevelopers.moengage.enums.Status.ERROR,e);
            } finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }


        } catch (IOException e) {
            // status code if we get some exception while oping a url
            responseObject = new HttpResponseObject(0,
                    e.getMessage(), com.raydevelopers.moengage.enums.Status.ERROR,e);
        }

        return responseObject;
    }

    @Override
    protected void onPostExecute(HttpResponseObject responseObject) {
        super.onPostExecute(responseObject);
        mListener.onResultReceived(responseObject);
    }
}
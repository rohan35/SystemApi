package com.raydevelopers.moengage.network;

import com.raydevelopers.moengage.enums.Status;

public class HttpResponseObject {
    private int statusCode;
    private String responseBody;
    private Status status;
    private Throwable error;

    public HttpResponseObject(int statusCode,String responseBody,Status status,Throwable error)
    {
        this.statusCode = statusCode;
        this.responseBody =responseBody;
        this.status = status;
        this.error = error;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public Status getStatus() {
        return status;
    }

    public Throwable getError() {
        return error;
    }
}

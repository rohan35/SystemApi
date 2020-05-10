package com.raydevelopers.moengage.network;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.raydevelopers.moengage.enums.Status;

public class NetworkResource<T> {

    public final int statusCode;

    @NonNull
    public final Status status;

    @Nullable
    public final T data;

    @Nullable
    public Throwable error;


     private NetworkResource(int statusCode, @NonNull Status status, @Nullable T data,
                            @Nullable Throwable error) {
        this.statusCode = statusCode;
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> NetworkResource<T> success(@NonNull T data, int statusCode) {
        return new NetworkResource<>(statusCode, Status.SUCCESS, data, null);
    }

    public static <T> NetworkResource<T> error(@NonNull T data, @NonNull Throwable error, int statusCode) {
        return new NetworkResource<>(statusCode, Status.ERROR, data, error);
    }
}

package com.clarioninnovative.mvpdemo.api;

import retrofit2.Retrofit;

/**
 * Created by Ramesh on 10/10/18.
 */

public abstract class BaseRetrofitProvider<T> {
    private T service;

    public BaseRetrofitProvider(Retrofit retrofit, Class<T> clzz) {
        service = retrofit.create(clzz);
    }

    public T getService() {
        return (T) service;
    }
}

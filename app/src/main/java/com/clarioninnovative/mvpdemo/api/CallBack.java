package com.clarioninnovative.mvpdemo.api;

/**
 * Created by Ramesh on 09/10/18.
 */

public interface CallBack<T> {

    void onSuccess(T result);

    void onFailure(String message);
}

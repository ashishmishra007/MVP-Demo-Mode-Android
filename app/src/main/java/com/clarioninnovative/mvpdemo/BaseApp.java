package com.clarioninnovative.mvpdemo;

import android.app.Application;

import com.clarioninnovative.mvpdemo.api.DemoGitServiceProvider;
import com.clarioninnovative.mvpdemo.api.GitProvider;
import com.clarioninnovative.mvpdemo.api.RetrofitGitServiceProvider;

/**
 * Created by Ramesh on 09/10/18.
 */

public class BaseApp extends Application {
    private boolean IS_DEMO = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public GitProvider getGitProvider() {
        if (IS_DEMO) {
            return new DemoGitServiceProvider(this);
        } else {
            return new RetrofitGitServiceProvider();
        }
    }
}

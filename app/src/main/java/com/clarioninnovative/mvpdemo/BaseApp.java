package com.clarioninnovative.mvpdemo;

import android.app.Application;

import com.clarioninnovative.mvpdemo.api.ApiService;
import com.clarioninnovative.mvpdemo.api.DemoGitServiceProvider;
import com.clarioninnovative.mvpdemo.api.GitProvider;
import com.clarioninnovative.mvpdemo.api.RetrofitGitServiceProvider;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ramesh on 09/10/18.
 */

public class BaseApp extends Application {
    private boolean IS_DEMO = false;
    private final static String BASE_URL = "https://api.github.com";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public GitProvider getGitProvider() {
        if (IS_DEMO) {
            return new DemoGitServiceProvider(this);
        } else {
            OkHttpClient okHttpClient = buildOkHttpClient();
            Retrofit retrofit = getRetrofit(BASE_URL, okHttpClient);
            return new RetrofitGitServiceProvider(retrofit);
        }
    }


    <T> Retrofit getRetrofit(String baseUrl, OkHttpClient defaultHttpClient) {
        //Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(defaultHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    //

    private OkHttpClient buildOkHttpClient() {
        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient defaultHttpClient = builder
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .build();
                        return chain.proceed(request);
                    }
                })
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        return defaultHttpClient;
    }
}

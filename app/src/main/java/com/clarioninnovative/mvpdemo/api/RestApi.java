package com.clarioninnovative.mvpdemo.api;


import com.clarioninnovative.mvpdemo.BuildConfig;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Ramesh on 14/07/18.
 */

public class RestApi {

    private static RestApi mRestApi;
    private final static String BASE_URL = "https://api.github.com";

    public static synchronized RestApi getInstance() {
        if (mRestApi == null) {
            mRestApi = new RestApi();
        }
        return mRestApi;
    }


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

    <T> T buildAdapter(String baseUrl, OkHttpClient defaultHttpClient, Class<T> clazz) {
        //Build Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .client(defaultHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }
    //

    public ApiService getService() {
        return buildAdapter(BASE_URL, buildOkHttpClient(), ApiService.class);
    }


    public interface ApiService {

        //https://api.github.com/orgs/square/repos
        @GET("orgs/{orgName}/repos?page=1&per_page=5")
        Call<List<Repos>> getRepos(@Path("orgName") String orgName);
    }
}

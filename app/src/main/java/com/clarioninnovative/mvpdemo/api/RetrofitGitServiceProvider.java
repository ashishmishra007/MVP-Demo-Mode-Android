package com.clarioninnovative.mvpdemo.api;

/**
 * Created by Ramesh on 09/10/18.
 */

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitGitServiceProvider extends BaseRetrofitProvider<ApiService> implements GitProvider {

    public RetrofitGitServiceProvider(Retrofit retrofit) {
        super(retrofit, ApiService.class);
    }

    @Override
    public void getRepos(String owner, final CallBack<List<Repos>> callBack) {
        /*RestApi.getInstance().getService()*/
        getService().getRepos(owner).enqueue(new Callback<List<Repos>>() {
            @Override
            public void onResponse(@NonNull Call<List<Repos>> call, @NonNull Response<List<Repos>> response) {
                if (response.isSuccessful()) {
                    callBack.onSuccess(response.body());
                } else {
                    try {
                        callBack.onFailure(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Repos>> call, @NonNull Throwable t) {
                callBack.onFailure("Error! " + t.getMessage());
            }
        });
    }
}

package com.clarioninnovative.mvpdemo.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    //https://api.github.com/orgs/square/repos
    @GET("orgs/{orgName}/repos?page=1&per_page=5")
    Call<List<Repos>> getRepos(@Path("orgName") String orgName);
}
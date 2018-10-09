package com.clarioninnovative.mvpdemo.api;

import java.util.List;

public interface GitProvider {
    void getRepos(String owner, CallBack<List<Repos>> callBack);
}


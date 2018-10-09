package com.clarioninnovative.mvpdemo.mvp;

import com.clarioninnovative.mvpdemo.api.GitProvider;
import com.clarioninnovative.mvpdemo.api.Repos;

import java.util.List;

/**
 * Created by Ramesh on 09/10/18.
 */

public interface GithubPresenter extends BasePresenter {

    void loadReposByOrgName(String orgName);

    void setUiCallBack(UiCallBack callBack);

    void setGitProvider(GitProvider gitProvider);

    public interface UiCallBack {
        void showLoading(boolean isLoading);

        void onSuccess(List<Repos> list);

        void onFailure();
    }
}

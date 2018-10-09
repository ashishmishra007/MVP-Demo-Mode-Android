package com.clarioninnovative.mvpdemo.mvp;

import com.clarioninnovative.mvpdemo.api.CallBack;
import com.clarioninnovative.mvpdemo.api.GitProvider;
import com.clarioninnovative.mvpdemo.api.Repos;

import java.util.List;

/**
 * Created by Ramesh on 09/10/18.
 */

public class GithubPresenterImpl implements GithubPresenter {

    UiCallBack callBack;
    GitProvider gitProvider;

    @Override
    public void setUiCallBack(UiCallBack callBack) {
        this.callBack = callBack;
    }

    public void setGitProvider(GitProvider gitProvider) {
        this.gitProvider = gitProvider;
    }

    @Override
    public void loadReposByOrgName(String orgName) {
        if (callBack != null) {
            callBack.showLoading(true);
        }
        gitProvider.getRepos(orgName, new CallBack<List<Repos>>() {
            @Override
            public void onSuccess(List<Repos> result) {
                if (callBack != null) {
                    callBack.showLoading(false);
                    callBack.onSuccess(result);
                }
            }

            @Override
            public void onFailure(String message) {
                if (callBack != null) {
                    callBack.showLoading(false);
                    callBack.onFailure();
                }

            }
        });
    }

    @Override
    public void dispose() {
        callBack = null;
    }
}

package com.clarioninnovative.mvpdemo.api;

import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DemoGitServiceProvider implements GitProvider {
    final Context mContext;

    public DemoGitServiceProvider(Context context) {
        mContext = context;
    }

    @Override
    public void getRepos(String owner, final CallBack<List<Repos>> callBack) {
        String repoStr = null;
        try {
            repoStr = readTextFromAssets("repos.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Repos> resultList = new Gson().fromJson(repoStr, new TypeToken<List<Repos>>() {
        }.getType());
        //
        //Delayed result for 2 seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(resultList);
            }
        }, 2000);

    }

    private String readTextFromAssets(String fileName) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(mContext.getAssets().open(fileName)));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}
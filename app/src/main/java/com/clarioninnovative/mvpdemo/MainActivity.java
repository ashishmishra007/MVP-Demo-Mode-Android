package com.clarioninnovative.mvpdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.clarioninnovative.mvpdemo.api.Repos;
import com.clarioninnovative.mvpdemo.mvp.GithubPresenter;
import com.clarioninnovative.mvpdemo.mvp.GithubPresenterImpl;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, GithubPresenter.UiCallBack {
    private ProgressBar progressBar;
    private EditText editText;
    GithubPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        editText = findViewById(R.id.editText);
        findViewById(R.id.button).setOnClickListener(this);
        getPresenter().setUiCallBack(this);
        getPresenter().setGitProvider(getBaseApp().getGitProvider());
    }

    public BaseApp getBaseApp() {
        return (BaseApp) getApplication();
    }

    public GithubPresenter getPresenter() {
        if (presenter == null) {
            presenter = new GithubPresenterImpl();
        }
        return presenter;
    }

    @Override
    public void onClick(View v) {
        getPresenter().loadReposByOrgName(editText.getText().toString().trim());
    }

    @Override
    public void showLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSuccess(List<Repos> list) {
        Toast.makeText(this, "Result received:" + list.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailure() {
        Toast.makeText(this, "API Failed", Toast.LENGTH_SHORT).show();
    }
}

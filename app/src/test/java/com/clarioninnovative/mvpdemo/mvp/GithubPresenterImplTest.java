package com.clarioninnovative.mvpdemo.mvp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by Ramesh on 10/10/18.
 */

public class GithubPresenterImplTest {

    GithubPresenterImpl presenter;
    GithubPresenter.UiCallBack callBack;

    @Before
    public void setUp() {
        presenter = Mockito.spy(new GithubPresenterImpl());
        callBack = Mockito.mock(GithubPresenter.UiCallBack.class);
    }

    @Test
    public void test_setUiCallBack_NonNull() {
        presenter.setUiCallBack(callBack);
        Assert.assertNotNull(presenter.callBack);
    }

    @Test
    public void test_Dispose() {
        presenter.dispose();
        Assert.assertNull(presenter.callBack);
    }


}

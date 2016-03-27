package com.livingstoneapp.presenters;


import com.livingstoneapp.interactors.IActivityInteractor;
import com.livingstoneapp.views.IActivitiesFragmentView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Matchers.anyListOf;

import static org.junit.Assert.*;

/**
 * Created by Renier on 2016/03/15.
 */
public class ActivitiesFragmentPresenterImplTest {

    @Mock
    IActivitiesFragmentView mView;

    @Mock
    IActivityInteractor mInteractor;

    private IActivitiesFragmentPresenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new ActivitiesFragmentPresenterImpl(mInteractor);
        mPresenter.setView(mView);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInitWithPackages() {
        assertTrue(false);
    }

    @Test
    public void testInitNoActivities() {
        assertTrue(false);
    }

    @Test
    public void testNoViewInit() {
        assertTrue(false);
    }

    @Test
    public void testInitError() {
        assertTrue(false);
    }

    @Test
    public void testActivitySelected() {
        assertTrue(false);
    }

    @Test
    public void testNoViewActivitySelected() {
        assertTrue(false);
    }
}
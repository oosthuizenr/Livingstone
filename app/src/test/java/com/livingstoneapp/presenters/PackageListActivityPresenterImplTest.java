package com.livingstoneapp.presenters;

import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.models.InstalledPackageHeader;
import com.livingstoneapp.presenters.utils.RxSchedulersOverrideRule;
import com.livingstoneapp.views.IPackageListView;

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

/**
 * Created by Renier on 2016/03/11.
 */

@RunWith(MockitoJUnitRunner.class)
public class PackageListActivityPresenterImplTest {

    @Mock
    IPackageListView mView;
    @Mock
    IPackageInteractor mPackageInteractor;

    private IPackageListActivityPresenter mPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new PackageListActivityPresenterImpl(mPackageInteractor);
        mPresenter.setView(mView);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() {
        mPresenter.init();

        verify(mView, times(1)).init();
    }

    @Test
    public void testLoadPackages() {
        ArrayList<InstalledPackageHeader> toReturn = new ArrayList<>();
        toReturn.add(Mockito.mock(InstalledPackageHeader.class));

        when(mPackageInteractor.getInstalledPackages(false)).thenReturn(Observable.just(toReturn));

        mPresenter.loadPackages(false);

        verify(mView, times(1)).hideContentView();
        verify(mView, times(1)).showWaitDialog();

        verify(mView, times(1)).setInstalledPackages(toReturn);
        verify(mView, times(1)).showContentView();
        verify(mView, times(1)).hideWaitDialog();
    }

    @Test
    public void testLoadPackagesFail() {
        String errorMessage = "Test Error";

        when(mPackageInteractor.getInstalledPackages(false)).thenReturn(Observable.error(new Throwable(errorMessage)));

        mPresenter.loadPackages(false);

        verify(mView, times(1)).hideContentView();
        verify(mView, times(1)).showWaitDialog();

        verify(mView, times(1)).hideWaitDialog();
        verify(mView, times(1)).setError(errorMessage);

        verify(mView, never()).setInstalledPackages(anyListOf(InstalledPackageHeader.class));
        verify(mView, never()).showContentView();
    }

    @Test
    public void testEmptyListOfPackages() {
        String errorMessage = "No Packages";

        when(mPackageInteractor.getInstalledPackages(false)).thenReturn(Observable.error(new Throwable(errorMessage)));

        mPresenter.loadPackages(false);

        verify(mView, times(1)).hideContentView();
        verify(mView, times(1)).showWaitDialog();

        verify(mView, times(1)).hideWaitDialog();
        verify(mView, times(1)).setError(errorMessage);

        verify(mView, never()).setInstalledPackages(anyListOf(InstalledPackageHeader.class));
        verify(mView, never()).showContentView();
    }

    @Test
    public void testNoViewAfterLoadPackages() {
        ArrayList<InstalledPackageHeader> toReturn = new ArrayList<>();

        when(mPackageInteractor.getInstalledPackages(false)).thenReturn(Observable.timer(5000, TimeUnit.MILLISECONDS).flatMap((timer) -> Observable.just(toReturn)));
        mPresenter.loadPackages(false);

        mPresenter.clearView();

        verify(mView, never()).setInstalledPackages(anyListOf(InstalledPackageHeader.class));
        verify(mView, never()).showContentView();
        verify(mView, never()).hideWaitDialog();
        verify(mView, never()).setError(anyString());
    }

    @Test
    public void testNoViewBeforeLoadPackages() {
        ArrayList<InstalledPackageHeader> toReturn = new ArrayList<>();

        when(mPackageInteractor.getInstalledPackages(false)).thenReturn(Observable.just(toReturn));

        mPresenter.clearView();

        mPresenter.loadPackages(false);

        verify(mView, never()).hideContentView();
        verify(mView, never()).showWaitDialog();
        verify(mView, never()).setInstalledPackages(anyListOf(InstalledPackageHeader.class));
        verify(mView, never()).showContentView();
        verify(mView, never()).hideWaitDialog();
        verify(mView, never()).setError(anyString());
    }

    @Test
    public void testPackageSelectedSinglePane() {
        InstalledPackageHeader header = Mockito.mock(InstalledPackageHeader.class);

        mPresenter.packageSelected(header);

        verify(mView, times(1)).startDetailActivity(header);
        verify(mView, never()).setDetailFragment(header);
    }

    @Test
    public void testPackageSelectedTwoPane() {
        InstalledPackageHeader header = Mockito.mock(InstalledPackageHeader.class);

        mPresenter.setTwoPane();

        mPresenter.packageSelected(header);

        verify(mView, times(1)).setDetailFragment(header);
        verify(mView, never()).startDetailActivity(header);
    }

    @Test
    public void testNoViewPackageSelected() {
        InstalledPackageHeader header = Mockito.mock(InstalledPackageHeader.class);

        mPresenter.clearView();

        mPresenter.packageSelected(header);

        verify(mView, never()).startDetailActivity(header);
        verify(mView, never()).setDetailFragment(header);
    }
}
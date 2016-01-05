package com.seu.mycircurt.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.seu.mycircurt.R;
import com.seu.mycircurt.framework.Model;
import com.seu.mycircurt.framework.PresenterFragmentImpl;
import com.seu.mycircurt.framework.QueryEnum;
import com.seu.mycircurt.framework.UpdatableView;
import com.seu.mycircurt.framework.UserActionEnum;

import static com.seu.mycircurt.util.LogUtils.*;
import static com.seu.mycircurt.util.LogUtils.makeLogTag;

/**
 * A base activity that handles common functionality in the app.
 *
 * @author JeremyXu on 2016/1/14 via jeremy_xm@163.com
 * @version v0.0
 */
public abstract class BaseActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    public final static String PRESENTER_TAG = "Presenter";

    private static final String TAG = makeLogTag(BaseActivity.class);

    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        sp.registerOnSharedPreferenceChangeListener(this);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        this.getSupportActionBar();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * Adds a {@link com.seu.mycircurt.framework.PresenterFragmentImpl} to the
     * Activity if required, and sets it up with the {@code model}, {@code queries},
     * {@code actions} and the {@link com.seu.mycircurt.framework.UpdatableView}
     * corresponding to the {@code updatableViewResId}.
     *
     * @return the {@link com.seu.mycircurt.framework.PresenterFragmentImpl},
     */
    public PresenterFragmentImpl addPresenterFragment(int updatableViewResId, Model model, QueryEnum[] queries,
                                                      UserActionEnum[] actions) {
        FragmentManager fragmentManager = getFragmentManager();

        //Check if the presenter fragment is already present (ie if the activity is recreated due
        // to orientation change).
        PresenterFragmentImpl presenter = (PresenterFragmentImpl) fragmentManager.findFragmentByTag(
                PRESENTER_TAG);
        if (presenter == null) {
            //Create, set up and add the presenter.
            presenter = new PresenterFragmentImpl();
            setUpPresenter(presenter, fragmentManager, updatableViewResId, model, queries, actions);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(presenter, PRESENTER_TAG);
            fragmentTransaction.commit();
        } else {
            //Set up the presenter.
            setUpPresenter(presenter, fragmentManager, updatableViewResId, model, queries, actions);
        }
        return presenter;
    }

    /**
     * Registers the {@code presenter} as a
     * {@link com.seu.mycircurt.util.ThrottledContentObserver} for the given
     * {@code uri}. When the content is changed, the specified {@code queries} are run.
     */
    public void registerPresenterFragmentAsContentObserverForUri
    (PresenterFragmentImpl presenter, Uri uri, QueryEnum[] queries) {
        if (presenter != null) {
            presenter.registerContentObserverOnUri(uri, queries);
        } else {
            LOGE(TAG, "You must add the presenter using addPresenterFragment method before " +
                    "calling registerPresenterFragmentAsContentObserverForUri! Pass in the returned"
                    + " object from addPresenterFragment as first argument.");
        }
    }

    private void setUpPresenter(PresenterFragmentImpl presenter, FragmentManager fragmentManager,
                                int updatableViewResId, Model model, QueryEnum[] queries,
                                UserActionEnum[] actions) {
        UpdatableView ui = (UpdatableView) fragmentManager.findFragmentById(
                updatableViewResId);
        presenter.setModel(model);
        presenter.setUpdatableView(ui);
        presenter.setInitialQueriesToLoad(queries);
        presenter.setValidUserActions(actions);
    }
}

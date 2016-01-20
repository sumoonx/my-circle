/*
 * Copyright 2016 Team Wheat, CNV-2313
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seu.mycircle.ui.fragments;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seu.mycircle.R;
import com.seu.mycircle.WheatApplication;
import com.seu.mycircle.injector.components.DaggerLoginComponent;
import com.seu.mycircle.model.entities.AuthCredentials;
import com.seu.mycircle.mvp.presenters.LoginPresenter;
import com.seu.mycircle.mvp.views.LoginView;
import com.seu.mycircle.utils.KeyboardUtils;
import com.hkm.ui.processbutton.iml.ActionProcessButton;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Fragment to sh
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class LoginFragment extends BaseFragment implements LoginView {

    @Bind(R.id.username) EditText username;
    @Bind(R.id.password) EditText password;
    @Bind(R.id.loginButton) ActionProcessButton loginButton;
    @Bind(R.id.errorView) TextView errorView;
    @Bind(R.id.loginForm) ViewGroup loginForm;

    //Dagger does not support private field
    @Inject protected LoginPresenter presenter;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        injectDependencies();
    }

    @Override protected int getLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton.setMode(ActionProcessButton.Mode.ENDLESS);

        int startDelay = getResources().getInteger(android.R.integer.config_mediumAnimTime) + 100;
        LayoutTransition transition = new LayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        transition.setStartDelay(LayoutTransition.APPEARING, startDelay);
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, startDelay);
        loginForm.setLayoutTransition(transition);

        presenter.attachView(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @OnClick(R.id.loginButton) public void onLoginClicked() {
        // Check for empty fields
        String uname = username.getText().toString();
        String pass = password.getText().toString();

        loginForm.clearAnimation();

        if (TextUtils.isEmpty(uname)) {
            username.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
            username.startAnimation(shake);
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            password.clearAnimation();
            Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
            password.startAnimation(shake);
            return;
        }

        // Hide keyboard
        if (!KeyboardUtils.hideKeyboard(username)) {
             KeyboardUtils.hideKeyboard(password);
        }

        // Start login
        presenter.login(new AuthCredentials(uname, pass));
    }

    @Override public void showLoginForm() {
        errorView.setVisibility(View.GONE);
        setFormEnabled(true);
        loginButton.setProgress(0);
    }

    @Override public void loginSuccessful() {
        loginButton.setProgress(100); // We are done
        getActivity().finish();
        getActivity().overridePendingTransition(0, R.anim.zoom_out);
    }

    @Override public void showLoginFailed() {
        setFormEnabled(true);
        loginButton.setProgress(0);
        loginForm.clearAnimation();
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);
        loginForm.startAnimation(shake);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNetworkError() {
        setFormEnabled(true);
        loginButton.setProgress(0);
        loginForm.clearAnimation();
        Toast.makeText(getContext(), getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }

    //waited to be solved, when long time loading is needed will use this fuc.
    @Override public void showLoading() {
        errorView.setVisibility(View.GONE);
        setFormEnabled(false);
        // any progress between 0 - 100 shows animation
        loginButton.setProgress(30);
    }

    private void setFormEnabled(boolean enabled) {
        username.setEnabled(enabled);
        password.setEnabled(enabled);
        loginButton.setEnabled(enabled);
    }

    @Override protected void injectDependencies() {
        WheatApplication wheatApplication = (WheatApplication)getActivity().getApplication();
        DaggerLoginComponent.builder()
              .appComponent(wheatApplication.getAppComponent())
              .build().inject(this);
    }
}

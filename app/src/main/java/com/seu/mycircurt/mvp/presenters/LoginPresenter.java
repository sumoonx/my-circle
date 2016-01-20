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

package com.seu.mycircurt.mvp.presenters;

import com.seu.mycircurt.model.entities.AuthCredentials;
import com.seu.mycircurt.model.events.LoginSuccessfulEvent;
import com.seu.mycircurt.model.repository.Repository;
import com.seu.mycircurt.mvp.views.LoginView;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import rx.Subscriber;

/**
 * Respond to {@link LoginView} and send request to {@link Repository} if necessary.
 *
 * @author JeremyXu on 2016/1/14 via jeremy_xm@163.com
 * @version v0.0
 */
public class LoginPresenter implements MvpPresenter<LoginView> {
    private LoginView loginView;
    private Repository repository;
    private EventBus eventBus;

    @Inject
    public LoginPresenter(Repository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void login(AuthCredentials credentials) {
        if(isViewAttached()) {
            getView().showLoading();
        }
        repository.loginRequest(credentials)
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                            getView().showNetworkError();
                        }
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (isViewAttached()) {
                            if (aBoolean) {
                                getView().loginSuccessful();
                            } else {
                                getView().showLoginFailed();
                            }
                        }
                        eventBus.post(new LoginSuccessfulEvent());
                    }
                });
    }

    @Override
    public void attachView(LoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void detachView() {
        loginView = null;
    }

    @Override
    public LoginView getView() {
        return loginView;
    }

    @Override
    public boolean isViewAttached() {
        return loginView != null;
    }
}

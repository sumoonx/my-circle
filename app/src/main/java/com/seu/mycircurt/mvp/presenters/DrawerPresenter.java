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

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.Navigator;
import com.seu.mycircurt.model.events.LoginSuccessfulEvent;
import com.seu.mycircurt.model.repository.Repository;
import com.seu.mycircurt.mvp.DrawerManager;
import com.seu.mycircurt.mvp.views.DrawerView;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Respond to {@link DrawerView} and send request to {@link DrawerManager} if necessary.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class DrawerPresenter implements MvpPresenter<DrawerView> {
    private DrawerView drawerView;
    private DrawerManager drawerManager;

    private Context context;
    private EventBus eventBus;
    private Navigator navigator;

    private DrawerPresenter() {
    }

    @Inject public DrawerPresenter(Repository repository, EventBus eventBus,
                                   Navigator navigator, Context context) {
        drawerManager = new DrawerManager(repository, context);
        this.eventBus = eventBus;
        this.navigator = navigator;
        this.context = context;
    }

    @Override
    public void attachView(DrawerView v) {
        drawerView = v;
        eventBus.register(this);
    }

    @Override
    public void detachView() {
        eventBus.unregister(this);
    }

    @Override
    public boolean isViewAttached() {
        return drawerView != null;
    }

    @Override
    public DrawerView getView() {
        return drawerView;
    }

    public void checkLoginState() {
        if (isViewAttached()) {
            if (drawerManager.isLoggedIn()) {
                Logger.d("isLoggedIn");
                getView().showUserProfile(drawerManager.userProfileItem());
            }else {
                getView().hideUserProfile();
            }
            getView().showLabelItems(drawerManager.drawerLabels());
        }
    }

    public void onUserProfileClick() {
        if (drawerManager.isLoggedIn()) {
            navigator.startPeronalProfileActivity(context);
        }else {
            navigator.startLoginActivity(context);
        }
    }

    public void onMyReleaseClick() {
        if (drawerManager.isLoggedIn()) {
            navigator.startMyReleaseActivity(context);
        }else {
            navigator.showLoginNeeded(context);
        }
    }

    public void onMyMessageClick() {
        if (drawerManager.isLoggedIn()) {
            navigator.startMyMessageActivity(context);
        }else {
            navigator.showLoginNeeded(context);
        }
    }

    public void onLogoutClick() {
        Logger.d("try log out!");
    }

    public void onFeedbackClick() {
        navigator.startFeedbackActivity(context);
    }


    public void onUpdateClick() {
        Logger.d("update invalid!");
    }

    public void onAboutUsClick() {
        navigator.startAboutUsActivity(context);
    }

    public void onEventMainThread(LoginSuccessfulEvent event) {
        checkLoginState();
    }
}

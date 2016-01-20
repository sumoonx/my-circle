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

package com.seu.mycircurt.mvp;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;
import com.seu.mycircurt.model.entities.DrawerLabel;
import com.seu.mycircurt.model.entities.UserProfile;
import com.seu.mycircurt.model.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Manage all the data that the drawer would need
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class DrawerManager {
    private final static String BEFORE_LOGOUT = DrawerLabel.NAME_UPDATE;

    private Repository repository;
    private Context context;

    private UserProfile mUserProfile;
    private List<DrawerLabel> mDrawerLabels;

    //all DrawerLabels
    private DrawerLabel mMyReleaseDrawerLabel;
    private DrawerLabel mMyMessageDrawerLabel;
    private DrawerLabel mFeedbackDrawerLabel;
    private DrawerLabel mLoginOutDrawerLabel;
    private DrawerLabel mUpdateDrawerLabel;
    private DrawerLabel mAboutUsDrawerLabel;

    @Inject
    public DrawerManager(Repository repository, Context context) {
        this.repository = repository;
        this.context = context;
        createLabelItems();
        initLabelItems();
    }

    /*
     * check the user state
     */
    public boolean isLoggedIn() {
        return repository.isOnline();
    }

    /**
     * get available {@link UserProfile}
     * test purpose
     */
    public UserProfile userProfileItem(){
        if(mUserProfile == null) {
            mUserProfile = defaultUserProfile();
        }
        return mUserProfile;
    }

    public List<DrawerLabel> drawerLabels() {
        if(isLoggedIn()) {
            addLoginOutItem();
        }else {
            removeLoginOutItem();
        }
        return mDrawerLabels;
    }

    //test purpose
    private UserProfile defaultUserProfile() {
        return new UserProfile("Jeremy Xu", "Southeast University",
                BitmapFactory.decodeResource(context.getResources(), R.drawable.ted));

    }

    /**
     * add a login-out {@link DrawerLabel} if not exist
     */
    private void addLoginOutItem() {
        for (DrawerLabel i : mDrawerLabels) {
            if (i.getName().equals(DrawerLabel.NAME_LOGINOUT)) {
                break;
            }
            if (i.getName().equals( BEFORE_LOGOUT)) {
                mDrawerLabels.add(mDrawerLabels.indexOf(i), mLoginOutDrawerLabel);
                break;
            }
        }
    }

    /**
     * remove a login-out {@link DrawerLabel} if exist
     */
    private void removeLoginOutItem() {
        for (DrawerLabel i : mDrawerLabels) {
            if (i.getName().equals(DrawerLabel.NAME_LOGINOUT)) {
                mDrawerLabels.remove(i);
                break;
            }
        }
    }

    /**
     * create all {@link DrawerLabel}
     */
    private void createLabelItems() {
        mMyReleaseDrawerLabel = new DrawerLabel(DrawerLabel.NAME_MY_RELEASE,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_MY_RELEASE), 0);
        mMyMessageDrawerLabel = new DrawerLabel(DrawerLabel.NAME_MY_MESSAGE,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_MY_MESSAGE), 0);
        mFeedbackDrawerLabel = new DrawerLabel(DrawerLabel.NAME_FEEDBACK,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_FEEDBACK), 0);
        mLoginOutDrawerLabel = new DrawerLabel(DrawerLabel.NAME_LOGINOUT,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_LOGINOUT), 0);
        mUpdateDrawerLabel = new DrawerLabel(DrawerLabel.NAME_UPDATE,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_UPDATE), 0);
        mAboutUsDrawerLabel = new DrawerLabel(DrawerLabel.NAME_ABOUT_US,
                BitmapFactory.decodeResource(context.getResources(), DrawerLabel.RES_ABOUT_US), 0);
    }

    /**
     * add {@link DrawerLabel} to the list according to user state
     */
    private void initLabelItems(){
        if (mDrawerLabels == null) {
            mDrawerLabels = new ArrayList<>();
        }else {
            mDrawerLabels.clear();
        }
        mDrawerLabels.add(mMyReleaseDrawerLabel);
        mDrawerLabels.add(mMyMessageDrawerLabel);
        mDrawerLabels.add(mFeedbackDrawerLabel);
        mDrawerLabels.add(mUpdateDrawerLabel);
        mDrawerLabels.add(mAboutUsDrawerLabel);
        drawerLabels();
    }
}

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

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.seu.mycircle.R;
import com.seu.mycircle.WheatApplication;
import com.seu.mycircle.injector.components.DaggerDrawerComponent;
import com.seu.mycircle.injector.modules.DrawerModule;
import com.seu.mycircle.model.entities.DrawerLabel;
import com.seu.mycircle.model.entities.UserProfile;
import com.seu.mycircle.mvp.presenters.DrawerPresenter;
import com.seu.mycircle.ui.adapter.LabelItemListAdapter;
import com.seu.mycircle.mvp.views.DrawerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

/**
 * Fragment to show the drawer.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class DrawerFragment extends BaseFragment implements DrawerView {

    @Bind(R.id.drawer_header_name) TextView nickname;
    @Bind(R.id.drawer_header_school) TextView school;
    @Bind(R.id.drawer_header_icon) ImageView icon;
    @Bind(R.id.drawer_header_background) ImageView background;
    @Bind(R.id.drawer_error_not_login) TextView notLogin;
    @Bind(R.id.drawer_label_recyclerview) RecyclerView labelRecyclerView;

    //set xml layout file for this fragment
    @Override protected int getLayoutRes() {
        return R.layout.fragment_drawer;
    }

    @Inject DrawerPresenter presenter;
    private LabelItemListAdapter labelItemListAdapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        injectDependencies();
        presenter.attachView(this);

        initialUserProfile();
        initialDrawerLabels();

        presenter.checkLoginState();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void showUserProfile(UserProfile userProfile) {
        nickname.setVisibility(View.VISIBLE);
        school.setVisibility(View.VISIBLE);
        notLogin.setVisibility(View.GONE);

        nickname.setText(userProfile.getName());
        school.setText(userProfile.getRegionName());
        icon.setImageBitmap(userProfile.getIcon());
    }

    @Override
    public void hideUserProfile() {
        nickname.setVisibility(View.GONE);
        school.setVisibility(View.GONE);
        notLogin.setVisibility(View.VISIBLE);

        icon.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.unknown));
    }

    @Override
    public void showLabelItems(List<DrawerLabel> drawerLabels) {
        labelItemListAdapter.setDrawerLabels(drawerLabels);
        labelRecyclerView.setAdapter(labelItemListAdapter);
    }

    @Override
    public void decrementMessage() {

    }

    @Override
    public void increaseMessage() {

    }

    @Override protected void injectDependencies() {
        WheatApplication wheatApplication = (WheatApplication)getActivity().getApplication();
        DaggerDrawerComponent.builder()
                .drawerModule(new DrawerModule(getContext()))
                .appComponent(wheatApplication.getAppComponent())
                .build().inject(this);
    }

    private void initialUserProfile() {
        nickname.setOnClickListener(userProfileClickListener);
        school.setOnClickListener(userProfileClickListener);
        icon.setOnClickListener(userProfileClickListener);
        background.setOnClickListener(userProfileClickListener);
        notLogin.setOnClickListener(userProfileClickListener);
    }

    private void initialDrawerLabels() {
        labelRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        labelItemListAdapter = new LabelItemListAdapter(getActivity(), labelClickListener);
        labelRecyclerView.setAdapter(labelItemListAdapter);
    }

    private View.OnClickListener userProfileClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onUserProfileClick();
        }
    };

    private LabelItemListAdapter.ItemClickListener labelClickListener = new LabelItemListAdapter.ItemClickListener() {
        @Override
        public void onLabelItemClick(int position) {
            if (labelItemListAdapter.getItemCount() == 5) {
                position = position > 2 ? position + 1 : position;
            }
            switch (position) {
                case 0:
                    presenter.onMyReleaseClick();
                    break;
                case 1:
                    presenter.onMyMessageClick();
                    break;
                case 2:
                    presenter.onFeedbackClick();
                    break;
                case 3:
                    presenter.onLogoutClick();
                    break;
                case 4:
                    presenter.onUpdateClick();
                    break;
                case 5:
                    presenter.onAboutUsClick();
                    break;
                default:
                    break;
            }
            Logger.d("label item clicked at " + position);
        }
    };

//    @Override public void onItemClicked(DrawerItem item) {
//        //intentStarter.showMailsOfLabel(getActivity(), label);
//        switch (item.getName()){
//            case DrawerItem.NAME_MY_RELEASE:
//                intentStarter.showMyRelease(getContext());
//                break;
//            case DrawerItem.NAME_MY_MESSAGE:
//                intentStarter.showMyMessage(getContext());
//                break;
//            case DrawerItem.NAME_FEEDBACK:
//                intentStarter.showFeedBack(getContext());
//                break;
//            case DrawerItem.NAME_LOGINOUT:
//                break;
//            case DrawerItem.NAME_ABOUT_US:
//                intentStarter.showAboutUs(getContext());
//                break;
//            default:
//                break;
//        }
//    }

//    @Override public void decrementUnreadCount(String label) {
//    for (DrawerItem it : adapter.getItems()) {
//        if (it.getName().equals(label)) {
//            it.decrementUnreadCount();
//            adapter.notifyDataSetChanged();
//            return;
//        }
//    }
//  }

//    @Override public void setAccount(Account account) {
//
//        DrawerViewState vs = (DrawerViewState) viewState;
//        vs.setAccount(account);
//
//        nickname.setText(getResources().getString(account.getRegion().getRegionNameResId()));
//        school.setText(getResources().getString(account.getRegion().getRegionId()));
//        icon.setImageResource(account.getImageRes());
//
//        school.setVisibility(View.VISIBLE);
//        nickname.setVisibility(View.VISIBLE);
//        icon.setVisibility(View.VISIBLE);
//    }
//
//    @Override public void showAuthenticationRequired() {
//        super.showAuthenticationRequired();
//
//        school.setVisibility(View.GONE);
//        nickname.setVisibility(View.GONE);
//        icon.setVisibility(View.GONE);
//    }
}

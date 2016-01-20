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

package com.seu.mycircurt.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;
import com.seu.mycircurt.ui.activities.MyMessageActivity;
import com.seu.mycircurt.ui.activities.MyReleaseActivity;
import com.seu.mycircurt.ui.activities.PersonalProfileActivity;
import com.seu.mycircurt.ui.activities.SettingsActivity;

/**
 * @deprecated
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyCircurtFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyCircurtFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCircurtFragment extends Fragment implements View.OnClickListener{
    private ImageButton personalProfileBtn;
    private ImageButton myReleaseBtn;
    private ImageButton myMessageBtn;
    private ImageButton settingBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_my_circurt, container, false);

        personalProfileBtn = (ImageButton) myView.findViewById(R.id.fmc_userdetail_imgbtn);
        personalProfileBtn.setOnClickListener(this);

        myReleaseBtn = (ImageButton) myView.findViewById(R.id.fmc_myreleasedetail_imgbtn);
        myReleaseBtn.setOnClickListener(this);

        myMessageBtn = (ImageButton) myView.findViewById(R.id.fmc_mymessagedetail_imgbtn);
        myMessageBtn.setOnClickListener(this);

        settingBtn = (ImageButton) myView.findViewById(R.id.fmc_settingdetail_imgbtn);
        settingBtn.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fmc_userdetail_imgbtn:
                Logger.d("personal profile btn clicked");
                startActivity(new Intent(getActivity(), PersonalProfileActivity.class));
                break;
            case R.id.fmc_myreleasedetail_imgbtn:
                startActivity(new Intent(getActivity(), MyReleaseActivity.class));
                break;
            case R.id.fmc_mymessagedetail_imgbtn:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.fmc_settingdetail_imgbtn:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}

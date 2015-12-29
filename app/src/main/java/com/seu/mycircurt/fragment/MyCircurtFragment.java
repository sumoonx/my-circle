package com.seu.mycircurt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;
import com.seu.mycircurt.social.MyMessageActivity;
import com.seu.mycircurt.commodity.MyReleaseActivity;
import com.seu.mycircurt.profile.PersonalProfilectivity;
import com.seu.mycircurt.settings.SettingsActivity;

/**
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

        myMessageBtn = (ImageButton) myView.findViewById(R.id.btn_edit_message);
        myMessageBtn.setOnClickListener(this);

        settingBtn = (ImageButton) myView.findViewById(R.id.fmc_setting_imgbtn);
        settingBtn.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fmc_userdetail_imgbtn:
                Logger.d("personal profile btn clicked");
                startActivity(new Intent(getActivity(), PersonalProfilectivity.class));
                break;
            case R.id.fmc_myreleasedetail_imgbtn:
                startActivity(new Intent(getActivity(), MyReleaseActivity.class));
                break;
            case R.id.btn_edit_message:
                startActivity(new Intent(getActivity(), MyMessageActivity.class));
                break;
            case R.id.fmc_setting_imgbtn:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}

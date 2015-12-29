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
import com.seu.mycircurt.market.SportCategoryActivity;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CategoryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CategoryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoryFragment extends Fragment implements View.OnClickListener {
    private ImageButton sportBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Logger.init();
        View myView = inflater.inflate(R.layout.fragment_category, container, false);
        sportBtn = (ImageButton) myView.findViewById(R.id.sport_category);
        sportBtn.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        Logger.d("view clicked");
        switch (v.getId()){
            case R.id.sport_category:
                Logger.d("starting sport activity");
                startActivity(new Intent(getActivity(), SportCategoryActivity.class));
                break;
            default:
                break;
        }
    }
}

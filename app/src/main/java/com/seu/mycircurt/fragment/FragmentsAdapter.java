package com.seu.mycircurt.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.seu.mycircurt.util.TabInfo;
import java.util.ArrayList;

/**
 * @author yqman
 * @date 12,26
 * 该类作为PagerScrollerActivity的一个属性
 */
public class FragmentsAdapter extends FragmentPagerAdapter {

    ArrayList<TabInfo> tabs    = null;
    Context            context = null;

    public FragmentsAdapter(Context context, FragmentManager fm, ArrayList<TabInfo> tabs) {
        super(fm);
        this.tabs = tabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        return tabs.get(index).getFragment();
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return tabs.size();
    }

}

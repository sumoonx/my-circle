package com.seu.mycircurt.market;

import com.seu.mycircurt.fragment.CategoryFragment;
import com.seu.mycircurt.fragment.FragmentsAdapter;
import com.seu.mycircurt.fragment.MyCircurtFragment;
import com.seu.mycircurt.fragment.ReleaseFragment;
import com.seu.mycircurt.market.PagerScrollerActivity;
import com.seu.mycircurt.util.TabInfo;

import java.util.ArrayList;
/**
 * @author yqman
 * @date 12,26
 * @function
 * 继承PagerScrollerActivity类，实现setTabsAndAdapter()方法，
 * 即在mainActivity底部添加几个菜单栏，以及每个菜单栏对应的fragment；
 */
public class MainActivity extends PagerScrollerActivity {
    @Override
    protected void setTabsAndAdapter() {
        // TODO Auto-generated method stub
        tabs = new ArrayList<TabInfo>();
        tabs.add(new TabInfo(0, "分类",  new CategoryFragment()));
        tabs.add(new TabInfo(1, "发布",  new ReleaseFragment()));
        tabs.add(new TabInfo(2, "我的",  new MyCircurtFragment()));
        this.adapter = new FragmentsAdapter(this,getSupportFragmentManager(),tabs);
    }
}

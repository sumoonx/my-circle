package com.seu.mycircurt.market;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.seu.mycircurt.R;
import com.seu.mycircurt.fragment.FragmentsAdapter;
import com.seu.mycircurt.util.TabInfo;
import com.seu.mycircurt.util.TitleIndicator;

import java.util.ArrayList;
/**
 * @author yqman
 * @date 12,26
 * @function 实现mainactivity的功能；使用方式大体上跟Activity类似
 * MainActivity实现这个抽象类；
 */
public abstract class PagerScrollerActivity extends FragmentActivity implements OnPageChangeListener {
    private final String TAG="ActivityPagerScroller";
    private ViewPager viewPager;
    protected FragmentsAdapter   adapter;
    protected ArrayList<TabInfo> tabs;
    private TitleIndicator title;
    private int          defaultTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //该语句用于设置全屏显示
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() throws IndexOutOfBoundsException, NullPointerException {
        setTabsAndAdapter();

        viewPager = (ViewPager) findViewById(R.id.vPager);
        // 设置viewpager内部页面之间的间距
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.fragment_viewpager_margin));
        // 设置viewpager内部页面间距的drawable
        viewPager.setPageMarginDrawable(R.color.myblue);
        viewPager.setAdapter(adapter);
        
        //必须让viewPager设置此OnPageChangeListener的实现类，才能对滑动和页面状态监听
        viewPager.setOnPageChangeListener(this);
        Log.d(TAG, "(TitleIndicator) findViewById(R.id.title)");
        title = (TitleIndicator) findViewById(R.id.vPager_title);
        Log.d(TAG, "title.setNeedPortLine(true)");
        title.setNeedPortLine(true); //设置是否需要底部分割线
        Log.d(TAG, "title.init(tabs, viewPager)");
        title.init(tabs, viewPager);
        //判断默认页面数值是否溢出
        try {
            if (0 > defaultTab || defaultTab >= tabs.size()) {
                Log.v("default", String.valueOf(defaultTab));
                throw new IndexOutOfBoundsException();
            } else {

                title.setDefaultTab(defaultTab);
                viewPager.setCurrentItem(defaultTab);
            }

        } catch (NullPointerException e) {
            throw e;

        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //读者可在纸上画出多个页面的示意图，自己进行计算便能得出这个结果
        title.onScroll((viewPager.getWidth() + viewPager.getPageMargin()) * position + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        title.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        
    }

    public void setDefaultTab(int index) {
        this.defaultTab = index;
    }
    

    protected abstract void setTabsAndAdapter();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

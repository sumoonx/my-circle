package com.seu.mycircurt.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.seu.mycircurt.R;
import com.seu.mycircurt.fragment.CategoryFragment;
import com.seu.mycircurt.fragment.MyCircurtFragment;
import com.seu.mycircurt.fragment.ReleaseFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 用于展示分类目录的Fragment
     */
    private CategoryFragment categoryFragment;

    /**
     * 用于展示发布信息的Fragment
     */
    private ReleaseFragment releaseFragment;

    /**
     * 用于展示我的信息的Fragment
     */
    private MyCircurtFragment myCircurtFragment;

    /**
     * 分类目录界面布局
     */
    private View categoryLayout;

    /**
     * 发布信息界面布局
     */
    private View releaseLayout;

    /**
     * 我的信息界面布局
     */
    private View myInfoLayout;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fragmentManager = getFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

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
    /**
     * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
     */

    private void initViews() {
        categoryLayout = findViewById(R.id.category_layout);
        releaseLayout = findViewById(R.id.release_layout);
        myInfoLayout = findViewById(R.id.my_info_layout);
        categoryLayout.setOnClickListener(this);
        releaseLayout.setOnClickListener(this);
        myInfoLayout.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.category_layout:
                // 当点击了消息tab时，选中第1个tab
                setTabSelection(0);
                break;
            case R.id.release_layout:
                // 当点击了联系人tab时，选中第2个tab
                setTabSelection(1);
                break;
            case R.id.my_info_layout:
                // 当点击了动态tab时，选中第3个tab
                setTabSelection(2);
                break;
            default:
                break;
        }

    }

    /**
     * 根据传入的index参数来设置选中的tab页。
     *
     * @param index
     * 每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
     */
    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                categoryLayout.setBackgroundColor(0xff0000ff);
                if (categoryFragment == null) {
                    // 如果MessageFragment为空，则创建一个并添加到界面上
                    categoryFragment = new CategoryFragment();
                    transaction.add(R.id.content, categoryFragment);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(categoryFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
                releaseLayout.setBackgroundColor(0xff0000ff);
                if (releaseFragment == null) {
                    // 如果ContactsFragment为空，则创建一个并添加到界面上
                    releaseFragment = new ReleaseFragment();
                    transaction.add(R.id.content, releaseFragment);
                } else {
                    // 如果ContactsFragment不为空，则直接将它显示出来
                    transaction.show(releaseFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
                myInfoLayout.setBackgroundColor(0xff0000ff);
                if (myCircurtFragment == null) {
                    // 如果NewsFragment为空，则创建一个并添加到界面上
                    myCircurtFragment = new MyCircurtFragment();
                    transaction.add(R.id.content, myCircurtFragment);
                } else {
                    // 如果NewsFragment不为空，则直接将它显示出来
                    transaction.show(myCircurtFragment);
                }
                break;
            default:

        }
        transaction.commit();
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction
     * 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (categoryFragment != null) {
            transaction.hide(categoryFragment);
        }
        if (releaseFragment != null) {
            transaction.hide(releaseFragment);
        }
        if (myCircurtFragment != null) {
            transaction.hide(myCircurtFragment);
        }
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        categoryLayout.setBackgroundColor(0xffffffff);
        releaseLayout.setBackgroundColor(0xffffffff);
        myInfoLayout.setBackgroundColor(0xffffffff);
    }
}

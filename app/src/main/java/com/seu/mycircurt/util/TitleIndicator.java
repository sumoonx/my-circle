package com.seu.mycircurt.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import java.util.List;
/**
 * @author yqman
 * @date 12,26
 * @function 负责mainActivity底部视图的动态刷新
 * 该类作为PagerScrollerActivity的一个属性
 *
 */
public class TitleIndicator extends LinearLayout implements OnClickListener {
    private final String TAG="TitleIndicator";
    private int            currentTab      = 0;          // 当前页面
    private int            currentLocationlX;            // 滚动距离
    private int            totalTabs       = 0;          // 总页面数目
    private int            mWidth;                       // 此选项卡的宽度
    private int            mHeight;                      // 此选项卡的高度
    private int            cursorWidth;                  // 游标宽度
    private int            cursorHeight;                 // 游标高度
    private Path           cursorPath      = new Path(); // 游标轨迹

    private Path           bottomLine      = new Path();
    private boolean        initialize      = false;      // 是否初始化
    private Paint          paint           = new Paint(); // 画笔
    private boolean        needPortLine    = true;       // 时候需要底部分割线
    private ViewPager viewPager;                    // 选项卡所依赖的viewpager
    private LayoutInflater inflater;
    private int            bottomLineThick = 2;

    public TitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "TitleIndicator");
        inflater = ((Activity) context).getLayoutInflater();
        // TODO Auto-generated constructor stub
    }

    public void init(List<TabInfo> tabs, ViewPager viewPager) {
        Log.d(TAG,"init");
        this.viewPager = viewPager;

        setWillNotDraw(false);// 调用invalidate()时强制调用onDraw()方法

        totalTabs = tabs.size();

        // 设置标题的每一个Tab的位置属性
        for (int i = 0; i < totalTabs; i++) {
            View view = tabs.get(i).getTab(inflater, this);
            LayoutParams lP = (LayoutParams) view.getLayoutParams();
            lP.gravity = Gravity.CENTER_VERTICAL;
            view.setOnClickListener(this);
            addView(view);
        }
        setCurrentTab(currentTab);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        Log.d(TAG,"onDraw");
        super.onDraw(canvas);

        if (!initialize) {
            initTab(canvas);
        }

        float scroll_x = 0;
        if (totalTabs != 0) {
            // 计算滚动条实际滚动距离
            scroll_x = (currentLocationlX - ((currentTab) * (mWidth + viewPager.getPageMargin()))) / totalTabs;
        } else {
            scroll_x = currentLocationlX;
        }

        cursorPath.rewind();// 清楚之前的路径状态

        float left_x = currentTab * cursorWidth + scroll_x;
        float right_x = (currentTab + 1) * cursorWidth + scroll_x;
        float top_y = 2; //mHeight - cursorHeight - 2;//设置游标在屏幕中的位置，即在mHeight下面
        float bottom_y =  cursorHeight+2;//mHeight - 2;

        cursorPath.moveTo(left_x, top_y); // 移动到基点到左上角
        cursorPath.lineTo(right_x, top_y); // 画线：基点到右上角
        cursorPath.lineTo(right_x, bottom_y);// 基点到右下角
        cursorPath.lineTo(left_x, bottom_y);// 基点到左下角
        cursorPath.close();

        canvas.drawPath(cursorPath, paint);

        // 底部分割线线条
        if (needPortLine && !initialize) {

            bottomLine.moveTo(0, mHeight);
            bottomLine.lineTo(0, mHeight - bottomLineThick);
            bottomLine.lineTo(mWidth, mHeight - bottomLineThick);
            bottomLine.lineTo(mWidth, mHeight);
            bottomLine.close();
        }
        canvas.drawPath(bottomLine, paint);

        initialize = true;
    }

    // 初始化此选项卡
    private void initTab(Canvas canvas) {
        Log.d(TAG,"initTab");
        mWidth = getWidth();
        mHeight = getHeight();
        if (totalTabs != 0) cursorWidth = mWidth / totalTabs;
        else cursorWidth = mWidth;
        cursorHeight = mHeight / 10;// 滚动条高度默认为标题栏的1/10
        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(255, 0x01, 0xA9, 0xDB);// 蓝色
    }

    // 滚动
    public void onScroll(int currentLocationlX) {
        Log.d(TAG,"onScroll");
        this.currentLocationlX = currentLocationlX;
        invalidate();

    }

    public void setCurrentTab(int i) {

        // 清除旧Tab的文字状态
        View view = getChildAt(currentTab);
        view.setSelected(false);

        currentTab = i;
        // 设置新Tab的文字状态
        view = getChildAt(currentTab);
        view.setSelected(true);
        invalidate();

        if (viewPager.getCurrentItem() != i) viewPager.setCurrentItem(i);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        setCurrentTab(v.getId());

    }

    public void setNeedPortLine(boolean needPortLine) {
        this.needPortLine = needPortLine;
    }

    public void setDefaultTab(int index) {
        this.currentTab = index;
    }
}

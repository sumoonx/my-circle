package com.seu.mycircurt.activity;

import android.app.Activity;
import android.os.Bundle;

import com.seu.mycircurt.R;

/**
 * @description:
 * @author: JeremyXu on 2015/12/25
 * @e-mail: jeremy_xm@163.com
 * @version: v0.0
 */
public class TestLayoutActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_release);
    }
}

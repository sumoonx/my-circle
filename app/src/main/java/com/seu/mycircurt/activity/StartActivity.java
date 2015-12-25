package com.seu.mycircurt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.seu.mycircurt.R;

/**
 * @description:
 * @author: JeremyXu on 2015/12/25
 * @e-mail: jeremy_xm@163.com
 * @version: v0.0
 */
public class StartActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(StartActivity.this, MainActivity.class));
        finish();
    }
}
package com.seu.mycircurt.welcome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.seu.mycircurt.market.MainActivity;

/**
 * @description:
 * @author: JeremyXu on 2015/12/25
 * @e-mail: jeremy_xm@163.com
 * @version: v0.0
 */
public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
}
package com.seu.mycircurt.welcome;

import android.content.Intent;
import android.os.Bundle;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.market.MainActivity;
import com.seu.mycircurt.ui.BaseActivity;

/**
 * Start any activity here
 *
 * @author JeremyXu on 2015/12/25 via jeremy_xm@163.com
 * @version v0.0
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                Logger.init();

        //start your activity here, remember to register it in the manifest file
        startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
        finish();
    }
}
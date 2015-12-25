package com.seu.mycircurt.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.seu.mycircurt.R;

/**
 * description
 * @author JeremyXu on 2015/12/25
 * e-mail jeremy_xm@163.com
 * @version v0.0
 */
public class SettingActivity extends Activity implements View.OnClickListener {
    private ImageView feedBackBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        feedBackBtn = (ImageView) findViewById(R.id.set_callback);
        feedBackBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_callback:
                feedBackAction();
                break;
            default:
                break;
        }
    }

    private void backOperation(){
        finish();
    }

    private void feedBackAction(){
        startActivity(new Intent(SettingActivity.this, FeedbackActivity.class));
    }
}

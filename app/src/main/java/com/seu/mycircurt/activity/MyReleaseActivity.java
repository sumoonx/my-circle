package com.seu.mycircurt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

/**
 * description
 * @author JeremyXu on 2015/12/25
 * e-mail jeremy_xm@163.com
 * @version v0.0
 */
public class MyReleaseActivity extends Activity implements View.OnClickListener{
    private ImageView backClick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_release);

        backClick = (ImageView) findViewById(R.id.my_reles_back);
        backClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_reles_back:
                backOperation();
                break;
            default:
                break;
        }
    }

    private void backOperation(){
        Logger.d("click back");
        finish();
    }
}

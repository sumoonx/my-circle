package com.seu.mycircurt.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.seu.mycircurt.R;

/**
 * description:
 * @author JeremyXu on 2015/12/25
 * e-mail: jeremy_xm@163.com
 * @version v0.0
 */
public class MyMessageActivity extends Activity implements View.OnClickListener{
    private ImageView backclick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);

        backclick = (ImageView) findViewById(R.id.my_msg_back);
        backclick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_msg_back:
                backOperation();
                break;
            default:
                break;
        }
    }

    private void backOperation(){

    }
}

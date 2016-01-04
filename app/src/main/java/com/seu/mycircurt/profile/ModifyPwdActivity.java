/**
 * description:
 * @author JeremyXu on 2015/12/25
 * e-mail jeremy_xm@163.com
 * @version v0.0
 */

package com.seu.mycircurt.profile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

public class ModifyPwdActivity extends Activity implements View.OnClickListener{

    private ImageView backClick;
    private Button commitModifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_pwd);

        backClick = (ImageView) findViewById(R.id.modify_pwd_back);
        backClick.setOnClickListener(this);
        commitModifyBtn = (Button) findViewById(R.id.modify_pwd_save);
        commitModifyBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.modify_pwd_back:
                backOperation();
                break;
            case R.id.modify_pwd_save:
                commitPwdChanges();
                break;
            default:
                break;
        }
    }

    private void backOperation(){
        Logger.d("click back");
        finish();
    }

    private void commitPwdChanges(){
        Logger.d("commit password changes");
        finish();
    }
}

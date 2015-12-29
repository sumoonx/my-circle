/**
 * description
 *  PersonalProfilectivity
 * @author jereemy xu
 * @time 2015/12/23
 */
package com.seu.mycircurt.profile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

public class PersonalProfilectivity extends Activity implements View.OnClickListener{

    private ImageView backClick;
    private ImageView modifyPwdClick;
    private Button commitClick;

    private EditText idEdit;
    private EditText signatureEdit;
    private EditText phoneNumberEdit;
    private EditText emailEdit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);

        backClick = (ImageView) findViewById(R.id.per_info_back);
        backClick.setOnClickListener(this);
        modifyPwdClick = (ImageView) findViewById(R.id.per_goto_pw);
        modifyPwdClick.setOnClickListener(this);
        commitClick = (Button) findViewById(R.id.per_info_save);
        commitClick.setOnClickListener(this);

        idEdit = (EditText) findViewById(R.id.per_edit_id);
        signatureEdit = (EditText) findViewById(R.id.per_edit_sig);
        phoneNumberEdit = (EditText) findViewById(R.id.per_edit_num);
        emailEdit = (EditText) findViewById(R.id.per_edit_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.per_info_back:
                backOperation();
                break;
            case R.id.per_goto_pw:
                modifyPwd();
                break;
            case R.id.per_info_save:
                commitChanges();
                break;
            default:
                break;
        }
    }

    private void backOperation(){
        Logger.d("click back");
        finish();
    }

    private void modifyPwd(){
        Logger.d("modify password");
        startActivity(new Intent(PersonalProfilectivity.this, ModifyPwdActivity.class));
    }

    private void commitChanges(){
        Logger.d("commit changes");
    }

}
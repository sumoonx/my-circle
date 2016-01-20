/*
 * Copyright 2016 Team Wheat, CNV-2313
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.seu.mycircle.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircle.R;

/**
 * Activity to show personal profile.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class PersonalProfileActivity extends Activity implements View.OnClickListener{

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
        setContentView(R.layout.activity_my_info);

        backClick = (ImageView) findViewById(R.id.my_info_back);
        backClick.setOnClickListener(this);
        modifyPwdClick = (ImageView) findViewById(R.id.my_info_intopw);
        modifyPwdClick.setOnClickListener(this);
        commitClick = (Button) findViewById(R.id.my_info_save);
        commitClick.setOnClickListener(this);

        idEdit = (EditText) findViewById(R.id.my_info_id_edit);
        signatureEdit = (EditText) findViewById(R.id.my_info_sig_edit);
        phoneNumberEdit = (EditText) findViewById(R.id.my_info_phone);
        emailEdit = (EditText) findViewById(R.id.my_info_email);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_info_back:
                backOperation();
                break;
            case R.id.my_info_intopw:
                modifyPwd();
                break;
            case R.id.my_info_save:
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
        startActivity(new Intent(PersonalProfileActivity.this, ModifyPwdActivity.class));
    }

    private void commitChanges(){
        Logger.d("commit changes");
    }

}
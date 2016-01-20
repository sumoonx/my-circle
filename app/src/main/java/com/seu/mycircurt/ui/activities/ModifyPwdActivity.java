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

package com.seu.mycircurt.ui.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

/**
 * Activity for modify the password of the current user.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */

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

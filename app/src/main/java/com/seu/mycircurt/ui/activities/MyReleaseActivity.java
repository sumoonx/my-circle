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
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.R;

/**
 * Activity to show all my releases.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class MyReleaseActivity extends Activity implements View.OnClickListener{
    private ImageView backClick;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rls);

        backClick = (ImageView) findViewById(R.id.my_rls_back);
        backClick.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_rls_back:
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

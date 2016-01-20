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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.seu.mycircle.WheatApplication;
import com.seu.mycircle.injector.components.DaggerActivityComponent;
import com.seu.mycircle.injector.modules.ActivityModule;

import butterknife.ButterKnife;
import icepick.Icepick;

 /**
 * Base class for Activities which already setup butterknife and icepick
  *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        injectDependencies();
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    protected void injectDependencies() {
        WheatApplication wheatApplication = (WheatApplication)getApplication();
        DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .appComponent(wheatApplication.getAppComponent())
                .build().inject(this);
    }
}

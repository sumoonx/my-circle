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

package com.seu.mycircle.injector.components;

import android.content.Context;

import dagger.Component;
import com.seu.mycircle.injector.Activity;
import com.seu.mycircle.injector.modules.ActivityModule;
import com.seu.mycircle.ui.activities.BaseActivity;

/**
 * A component for BaseActivity injection
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */

@Activity
@Component(dependencies = AppComponent.class,
        modules = ActivityModule.class)
public interface ActivityComponent {

    Context context();

    void inject(BaseActivity baseActivity);
}

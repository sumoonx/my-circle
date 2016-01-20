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

package com.seu.mycircle.model.entities;

import android.graphics.Bitmap;
import com.seu.mycircle.ui.fragments.DrawerFragment;

/**
 * User profile to be showed on the {@link DrawerFragment}
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class UserProfile {
    private String name;
    private String region;
    private Bitmap icon;

    private UserProfile() {}

    public UserProfile(String name, String region, Bitmap icon){
        this.name = name;
        this.region = region;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public String getRegionName() {
        return region;
    }

    public Bitmap getIcon() {
        return icon;
    }
}

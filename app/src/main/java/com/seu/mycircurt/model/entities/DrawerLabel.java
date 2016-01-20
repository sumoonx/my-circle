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

package com.seu.mycircurt.model.entities;

import android.graphics.Bitmap;
import com.seu.mycircurt.R;

/**
 * Label items for the drawer
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class DrawerLabel {
    public static final String NAME_MY_RELEASE = "我的发布";
    public static final int RES_MY_RELEASE = R.drawable.ic_inbox;

    public static final String NAME_MY_MESSAGE = "我的消息";
    public static final int RES_MY_MESSAGE = R.drawable.ic_send;

    public static final String NAME_FEEDBACK = "意见反馈";
    public static final int RES_FEEDBACK = R.drawable.ic_spam;

    public static final String NAME_LOGINOUT = "退出账号";
    public static final int RES_LOGINOUT = R.drawable.ic_delete;

    public static final String NAME_UPDATE = "软件更新";
    public static final int RES_UPDATE = R.drawable.ic_delete;

    public static final String NAME_ABOUT_US = "关于我们";
    public static final int RES_ABOUT_US = R.drawable.ic_delete;

    String name;
    Bitmap icon;
    int unreadCount;

    private DrawerLabel(){
    }

    public DrawerLabel(String name, Bitmap icon, int unreadCount){
        this.name = name;
        this.icon = icon;
        this.unreadCount = unreadCount;
    }

    public String getName() {
        return name;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }

    public void incrementUnreadCount() {
        unreadCount++;
    }

    public void decrementUnreadCount(){
        unreadCount--;
    }
}

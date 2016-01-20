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

package com.seu.mycircle;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.seu.mycircle.ui.activities.MyReleaseActivity;
import com.seu.mycircle.ui.activities.FeedbackActivity;
import com.seu.mycircle.ui.activities.LoginActivity;
import com.seu.mycircle.ui.activities.MyMessageActivity;
import com.seu.mycircle.ui.activities.PersonalProfileActivity;

/**
 * A simple helper class that helps to create and launch Intents. It checks if we our device is a
 * phone or a tablet app.
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */
public class Navigator {

    private boolean isTablet(Context context) {
        return context.getResources().getBoolean(R.bool.tablet);
    }

    public void showLoginNeeded(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.alert_dialog_title));
        builder.setMessage(context.getString(R.string.login_needed));
        builder.setPositiveButton(context.getString(R.string.i_know), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(context.getString(R.string.go_login), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startLoginActivity(context);
            }
        });
        builder.create().show();
    }

    public void startPeronalProfileActivity(Context context) {
        context.startActivity(new Intent(context, PersonalProfileActivity.class));
    }

    public void startLoginActivity(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    public void startMyReleaseActivity(Context context){
        context.startActivity(new Intent(context, MyReleaseActivity.class));
    }

    public void startMyMessageActivity(Context context){
        context.startActivity(new Intent(context, MyMessageActivity.class));
    }

    public void startFeedbackActivity(Context context){
        context.startActivity(new Intent(context, FeedbackActivity.class));
    }

    public void startAboutUsActivity(Context context){
        //context.startActivity(new Intent(context, AboutActivity.class));
    }
}

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

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.seu.mycircle.R;

import butterknife.Bind;
import icepick.Icepick;
import icepick.Icicle;

/**
 * Main Activity for Wheat application.
 *
 * @author JeremyXu on 2016/1/20 via jeremy_xm@163.com
 * @version v0.0
 */
public class MainActivity extends BaseActivity {

    @Icicle String toolbarTitle;

    @Bind(R.id.drawerLayout) DrawerLayout drawerLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;

    ActionBarDrawerToggle drawerToggle;

    private long exitTime;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar.inflateMenu(R.menu.search_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.search) {
                    //intentStarter.showSearch(MainActivity.this);
                    return true;
                }
                return false;
            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,
                R.string.drawer_close);
        drawerLayout.setDrawerListener(drawerToggle);
        if (toolbarTitle != null) {
            toolbar.setTitle(toolbarTitle);
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override public void onBackPressed() {
        long currTime = System.currentTimeMillis();
        if ((currTime - exitTime) > 1500) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.please_push_again_to_qiut), Toast.LENGTH_SHORT).show();
            exitTime = currTime;
        }else {
            finish();
            System.exit(0);
        }
    }
}


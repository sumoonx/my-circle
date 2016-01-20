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

/**
 * @deprecated
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class User {
    public static final int INVALID_USERID = 0;
    public static final int SYSTEM_USERID = Integer.MAX_VALUE;

    private int id;
    private String username;
    private String school;
    private int imageResource;
    private String resourceUri;

    private User() {}

    public User(int id, String username){
        this.id = id;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getResourceUri() {
        return resourceUri;
    }

    public String getSchool() {
        return school;
    }

    public boolean isValidUser() {
        return id != INVALID_USERID;
    }

    public boolean isSystemUser() {
        return id == SYSTEM_USERID;
    }
}

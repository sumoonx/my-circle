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

package com.seu.mycircurt.model.repository.wheat.response;

/**
 * 作为网络返回的数据存储层：Gson Bean；
 *
 * @author bingowang on 2016/1/19
 * @version v0.0
 */
public class LoginResponse {
    private String loginSuccess;
    private String connectionId;

    public String getLoginSuccess() {
        return loginSuccess;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public void setLoginSuccess(String loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    public String getConnectionId() {
        return connectionId;
    }
}

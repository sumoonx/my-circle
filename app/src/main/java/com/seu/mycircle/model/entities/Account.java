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
 * Account information
 *
 * @author JeremyXu on 2016/1/12 via jeremy_xm@163.com
 * @version v0.0
 */
public class Account {
    private String username;
    private AccountIdentity accountIdentity;

    public Account setUsername(String username) {
        this.username = username;
        return this;
    }

    public Account setAccountIdentity(AccountIdentity accountIdentity) {
        this.accountIdentity = accountIdentity;
        return this;
    }

    public Account removeAccountIdentity() {
        accountIdentity = null;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountIdentity getAccountIdentity() {
        return accountIdentity;
    }

    public boolean isIdentified() {
        return accountIdentity != null;
    }

}

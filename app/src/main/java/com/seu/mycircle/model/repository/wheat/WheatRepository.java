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

package com.seu.mycircle.model.repository.wheat;

import com.orhanobut.logger.Logger;
import com.seu.mycircle.model.entities.Account;
import com.seu.mycircle.model.entities.AccountIdentity;
import com.seu.mycircle.model.entities.AuthCredentials;
import com.seu.mycircle.model.repository.Repository;
import com.seu.mycircle.model.repository.toolbox.HttpManager;
import com.seu.mycircle.model.repository.wheat.response.LoginResponse;
import com.squareup.okhttp.OkHttpClient;

import retrofit.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * A WheatRepository implements {@link Repository} interface.
 * It has a {@link WheatApi} to interact with Wheat Serve
 *
 * @author bingowang on 2016/1/19
 * @version v0.0
 */
public class WheatRepository implements Repository{
    private WheatApi mWheatApi;//请求服务接口
    private Account account;

    private Retrofit.Builder builder = null;
    private OkHttpClient client = null;

    private WheatRepository() {

    }

    public WheatRepository(OkHttpClient client) {
        this.client = client;
        builder = HttpManager.getBuilder(client);
        account = new Account();
    }

    @Override
    public boolean isOnline() {
        return account.isIdentified();
    }

    /**
     * 完成用户发出登陆请求
     *
     * @param credentials of {@link AuthCredentials} 登陆用户信息
     * @return Observable<Boolean> 登陆成功的被观察者对象，提供给presenter使用
     */
    @Override
    public Observable<Boolean> loginRequest(AuthCredentials credentials) {
        createLoginService(WheatApi.BASE_URL);
        account.setUsername(credentials.getUsername());
        Logger.d("connecting...");
        return mWheatApi.loginRequest(credentials.getUsername(), credentials.getPassword())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<LoginResponse, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(LoginResponse loginResponse) {
                        boolean b = false;
                        Logger.d("get response...");
                        if (loginResponse.getLoginSuccess().equals("success")) {
                            b = true;
                            account.setAccountIdentity(new AccountIdentity(Integer.parseInt(loginResponse.getConnectionId())));
                            Logger.d(loginResponse.getConnectionId());
                        }
                        return Observable.just(b);
                    }
                });
    }

    protected void createLoginService(String url) {
        mWheatApi = builder.baseUrl(url).build()
                .create(WheatApi.class);
    }
}

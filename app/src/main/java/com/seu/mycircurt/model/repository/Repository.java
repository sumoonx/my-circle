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

package com.seu.mycircurt.model.repository;

import com.seu.mycircurt.model.entities.AuthCredentials;
import rx.Observable;

/**
 * Interface for all network request
 *
 * @author bingowangon 2016/1/19
 * @version v0.0
 */
public interface Repository {
    boolean isOnline();

    /**
     * 定义登陆请求接口
     *
     * @param credentials 登陆账户
     * @return Observable<Boolean> 登陆成功的被观察者对象
     */
    Observable<Boolean> loginRequest(AuthCredentials credentials);




}

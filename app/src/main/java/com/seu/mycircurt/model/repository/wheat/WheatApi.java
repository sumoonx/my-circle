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

package com.seu.mycircurt.model.repository.wheat;

import com.seu.mycircurt.model.repository.wheat.response.LoginResponse;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;
import rx.Observable;

/**
 * 定义所有网络请求服务接口
 *
 * @author bingowang on 2016/1/19
 * @version v0.0
 */
public interface WheatApi {
    String BASE_URL = "http://121.248.54.250:8080/AndroidLoginTest/";

    /**
     * 定义登陆请求服务接口
     *
     * \@FormUrlEncoded 规定发送表单数据
     * \@POST 发送POST请求，参数是url请求的相对地址
     * \@Field 定义表单数据
     * @param username 登陆名
     *         password 密码
     * @return Observable<LoginResponse> 登陆请求返回数据的被观察者
     */
    @FormUrlEncoded
    @POST("login.do?method=init")
    Observable<LoginResponse> loginRequest(@Field("username") String username, @Field("password") String password);

}

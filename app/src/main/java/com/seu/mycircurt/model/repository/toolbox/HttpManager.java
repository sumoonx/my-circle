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

package com.seu.mycircurt.model.repository.toolbox;

import com.orhanobut.logger.Logger;
import com.seu.mycircurt.BuildConfig;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Set singleton object of {@link Retrofit.Builder}
 *
 *  * @author bongowang on 2016/1/18 via jeremy_xm@163.com
 * @version v0.0
 */
public class HttpManager {

    private static Retrofit.Builder builder = null;

    public static Retrofit.Builder getBuilder(OkHttpClient client) {
        if (builder == null) {
            client.setReadTimeout(10, TimeUnit.MINUTES);
            client.setConnectTimeout(10, TimeUnit.MINUTES);
            client.setWriteTimeout(10, TimeUnit.MINUTES);

            //print
            client.interceptors().add(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    if (message.startsWith("{")) {
                        Logger.json(message);
                    } else {
                        Logger.i("ApiManger", message);
                    }
                }
            }));

            //add custom headers
            client.interceptors().add(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request().newBuilder()
                            .addHeader("platform", "android")
                            .addHeader("appVersion", BuildConfig.VERSION_NAME)
                            .build();
                    return chain.proceed(request);
                }
            });

            builder = new Retrofit.Builder()
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
        return builder;
    }
}

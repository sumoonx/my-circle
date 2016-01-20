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

package com.seu.mycircle.injector.modules;

import de.greenrobot.event.EventBus;
import com.seu.mycircle.WheatApplication;
import com.seu.mycircle.model.repository.Repository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Provide basic Objects for all
 *
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */

@Module
public class AppModule {
    private final WheatApplication wheatApplication;
    private final EventBus eventBus;
    private final Repository repository;

    public AppModule(WheatApplication wheatApplication, EventBus eventBus, Repository repository) {
        this.wheatApplication = wheatApplication;
        this.eventBus = eventBus;
        this.repository = repository;
    }

    @Provides @Singleton public WheatApplication provideWheatApplication() {
        return wheatApplication;
    }

    @Provides @Singleton public EventBus providesEventBus(){
        return eventBus;
    }

    @Provides @Singleton public Repository provideRepository() {
        return repository;
    }
}

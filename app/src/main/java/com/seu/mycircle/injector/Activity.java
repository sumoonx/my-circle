/**
 * Copyright (C) 2015 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package com.seu.mycircle.injector;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author JeremyXu on 2016/1/13 via jeremy_xm@163.com
 * @version v0.0
 */

@Scope @Retention(RUNTIME)
public @interface Activity {}

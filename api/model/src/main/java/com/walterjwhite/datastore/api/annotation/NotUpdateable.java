package com.walterjwhite.datastore.api.annotation;

import java.lang.annotation.*;

/** Marks a field as not updateable, once the value is set, it cannot be changed. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotUpdateable {}

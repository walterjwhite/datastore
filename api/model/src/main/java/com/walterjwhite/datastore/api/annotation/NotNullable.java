package com.walterjwhite.datastore.api.annotation;

import java.lang.annotation.*;

/** Marks a field as not insertable at the time the entity is created. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotNullable {}

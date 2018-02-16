package com.walterjwhite.datastore.api.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Indicates that the entity we are looking for is tagged with the specified tag name. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Tagged {
  /**
   * List of tags this entity must have to be selected. @NOTE: this feature was developed to aid in
   * selecting the EmailSenderAccount since multiple accounts may exist.
   */
  String[] value() default {};
}

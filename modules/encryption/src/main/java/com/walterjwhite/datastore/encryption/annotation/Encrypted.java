package com.walterjwhite.datastore.encryption.annotation;

import com.walterjwhite.encryption.enumeration.EncryptionType;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Indicates a field is encrypted and can be decrypted. */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypted {
  /** What type of encryption is used? */
  EncryptionType encryptionType();
}

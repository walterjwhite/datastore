package com.walterjwhite.datastore.encryption.listener;

import com.walterjwhite.datastore.encryption.annotation.Encrypted;
import com.walterjwhite.encryption.api.service.FieldEncryptionService;
import com.walterjwhite.google.guice.GuiceHelper;
import java.lang.reflect.Field;
import javax.naming.NamingException;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EncryptionListener {

  private final Logger LOGGER = LoggerFactory.getLogger(EncryptionListener.class);

  @PrePersist
  @PreUpdate
  public void onPre(Object e) throws NamingException {
    for (final Field field : e.getClass().getDeclaredFields()) {
      final Encrypted encrypted = field.getAnnotation(Encrypted.class);
      if (encrypted != null) {
        GuiceHelper.getGuiceInjector()
            .getInstance(FieldEncryptionService.class)
            .encrypt(e, field, encrypted.encryptionType());
      }
    }
  }

  @PostLoad
  public void onPostLoad(Object e) throws Exception {
    for (final Field field : e.getClass().getDeclaredFields()) {
      final Encrypted encrypted = field.getAnnotation(Encrypted.class);
      if (encrypted != null) {
        GuiceHelper.getGuiceInjector()
            .getInstance(FieldEncryptionService.class)
            .decrypt(e, field, encrypted.encryptionType());
      }
    }
  }
}

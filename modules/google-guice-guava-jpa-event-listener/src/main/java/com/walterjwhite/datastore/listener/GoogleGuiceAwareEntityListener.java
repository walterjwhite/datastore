package com.walterjwhite.datastore.listener;

import com.google.common.eventbus.AsyncEventBus;
import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.event.post.PostPersistEvent;
import com.walterjwhite.datastore.api.event.post.PostRemoveEvent;
import com.walterjwhite.datastore.api.event.post.PostUpdateEvent;
import com.walterjwhite.datastore.api.event.pre.PrePersistEvent;
import com.walterjwhite.datastore.api.event.pre.PreRemoveEvent;
import com.walterjwhite.datastore.api.event.pre.PreUpdateEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.google.guice.GuiceHelper;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple JPA listener that listens for JPA events and republishes them on EventBus. EventBus
 * registrations *MUST* happen before this.
 */
public class GoogleGuiceAwareEntityListener {
  private static final Logger LOGGER =
      LoggerFactory.getLogger(GoogleGuiceAwareEntityListener.class);

  protected final AsyncEventBus asyncEventBus;

  public GoogleGuiceAwareEntityListener() {
    super();

    LOGGER.info("initializing:");
    asyncEventBus = GuiceHelper.getGuiceInjector().getInstance(AsyncEventBus.class);
    LOGGER.info("initializing:" + asyncEventBus);
  }

  @PrePersist
  public void onPrePersist(Object o) {
    fireEvent(new PrePersistEvent((AbstractEntity) o));
  }

  @PreUpdate
  public void onPreUpdate(Object o) {
    fireEvent(new PreUpdateEvent((AbstractEntity) o));
  }

  @PreRemove
  public void onPreRemove(Object o) {
    fireEvent(new PreRemoveEvent((AbstractEntity) o));
  }

  @PostPersist
  public void onPostPersist(Object o) {
    fireEvent(new PostPersistEvent((AbstractEntity) o));
  }

  @PostUpdate
  public void onPostUpdate(Object o) {
    fireEvent(new PostUpdateEvent((AbstractEntity) o));
  }

  @PostRemove
  public void onPostRemove(Object o) {
    fireEvent(new PostRemoveEvent((AbstractEntity) o));
  }

  protected void fireEvent(AbstractJPAEvent event) {
    LOGGER.info("firing event:" + event + ":" + event.getEntity());
    asyncEventBus.post(event);
  }
}

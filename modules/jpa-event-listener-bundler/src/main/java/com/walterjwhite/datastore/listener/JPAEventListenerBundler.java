package com.walterjwhite.datastore.listener;

import com.walterjwhite.datastore.api.event.AbstractJPAEvent;
import com.walterjwhite.datastore.api.event.post.PostPersistEvent;
import com.walterjwhite.datastore.api.event.post.PostRemoveEvent;
import com.walterjwhite.datastore.api.event.post.PostUpdateEvent;
import com.walterjwhite.datastore.api.event.pre.PrePersistEvent;
import com.walterjwhite.datastore.api.event.pre.PreRemoveEvent;
import com.walterjwhite.datastore.api.event.pre.PreUpdateEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import java.util.concurrent.PriorityBlockingQueue;
import javax.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** this is duplicate code, instead just write an observer for these events */
public class JPAEventListenerBundler {
  private static final Logger LOGGER = LoggerFactory.getLogger(JPAEventListenerBundler.class);

  /**
   * Place events on a queue, take from the queue at regular intervals (unbounded Blocking Queue)
   */
  protected final PriorityBlockingQueue<AbstractJPAEvent> transactionLog;

  public JPAEventListenerBundler() {
    super();

    transactionLog = new PriorityBlockingQueue<>();
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
    transactionLog.add(event);
  }
}

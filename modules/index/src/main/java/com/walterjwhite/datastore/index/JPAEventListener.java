package com.walterjwhite.datastore.index;

import com.google.common.eventbus.Subscribe;
import com.walterjwhite.datastore.api.event.post.PostPersistEvent;
import com.walterjwhite.datastore.api.event.post.PostRemoveEvent;
import com.walterjwhite.datastore.api.event.post.PostUpdateEvent;
import com.walterjwhite.datastore.api.model.entity.AbstractEntity;
import com.walterjwhite.datastore.criteria.EntityTypeRepository;
import com.walterjwhite.google.guice.annotation.EventListener;
import com.walterjwhite.index.api.model.index.Index;
import com.walterjwhite.index.api.model.index.IndexableRecord;
import com.walterjwhite.index.api.service.IndexService;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** TODO: we should make this durable otherwise we *MAY* lose events. */
@EventListener
public class JPAEventListener {
  private static final Logger LOGGER = LoggerFactory.getLogger(JPAEventListener.class);

  protected final IndexService indexService;

  protected final EntityTypeRepository entityTypeRepository;
  // TODO: which index do we load these records into?
  // TODO: this should be configured
  protected Index index;

  @Inject
  public JPAEventListener(IndexService indexService, EntityTypeRepository entityTypeRepository) {
    super();
    this.indexService = indexService;
    this.entityTypeRepository = entityTypeRepository;
  }

  @Subscribe
  protected void processPersist(PostPersistEvent postPersistEvent) throws Exception {
    if (isIndexed(postPersistEvent.getEntity()))
      indexService.index(
          new IndexableRecord(
              index,
              entityTypeRepository.findOrCreate(postPersistEvent.getEntity().getClass()),
              postPersistEvent.getEntity().getId(),
              postPersistEvent.getEntity()));
  }

  @Subscribe
  protected void processUpdate(PostUpdateEvent postUpdateEvent) throws Exception {
    if (isIndexed(postUpdateEvent.getEntity()))
      indexService.update(
          new IndexableRecord(
              index,
              entityTypeRepository.findOrCreate(postUpdateEvent.getEntity().getClass()),
              postUpdateEvent.getEntity().getId(),
              postUpdateEvent.getEntity()));
  }

  @Subscribe
  protected void processDelete(PostRemoveEvent postRemoveEvent) throws Exception {
    if (isIndexed(postRemoveEvent.getEntity()))
      indexService.delete(
          new IndexableRecord(
              index,
              entityTypeRepository.findOrCreate(postRemoveEvent.getEntity().getClass()),
              postRemoveEvent.getEntity().getId(),
              null));
  }

  public boolean isIndexed(final AbstractEntity entity) {
    if (entity.getClass().getPackage().getName().startsWith("com.walterjwhite.job")) {
      //    if (entityType.startsWith("com.walterjwhite.job")) {

      LOGGER.info("entity (non-indexed):" + entity);
      return (false);
    }

    LOGGER.info("entity (indexing):" + entity);
    return (true);
  }
}

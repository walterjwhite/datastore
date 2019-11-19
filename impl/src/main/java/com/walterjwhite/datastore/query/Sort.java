package com.walterjwhite.datastore.query;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Sort {
  protected final String fieldName;
  protected final SortOrder sortOrder;
}

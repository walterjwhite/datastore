package com.walterjwhite.infrastructure.datastore.modules.jdbc.annotation;

import com.walterjwhite.google.guice.property.annotation.Sensitive;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

@Sensitive
public interface JDBCUsername extends GuiceProperty {}

package com.walterjwhite.datastore;

import com.walterjwhite.google.guice.property.property.DefaultValue;
import com.walterjwhite.google.guice.property.property.GuiceProperty;

public interface JPAUnit extends GuiceProperty {
  @DefaultValue String Default = "defaultJPAUnit";
}

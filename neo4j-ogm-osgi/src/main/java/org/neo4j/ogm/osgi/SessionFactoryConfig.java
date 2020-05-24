package org.neo4j.ogm.osgi;

import org.neo4j.ogm.config.Configuration;

import java.util.List;

public class SessionFactoryConfig {
  public Configuration driverConfiguration;
  public List<String> packages;

  public Configuration getDriverConfiguration() {
    return driverConfiguration;
  }

  public void setDriverConfiguration(Configuration driverConfiguration) {
    this.driverConfiguration = driverConfiguration;
  }

  public List<String> getPackages() {
    return packages;
  }

  public void setPackages(List<String> packages) {
    this.packages = packages;
  }
}

package org.neo4j.ogm.osgi;

import org.neo4j.ogm.osgi.utils.classloader.ExecutableExceptionableAction;
import org.neo4j.ogm.session.SessionFactory;

public class SessionFactoryAction implements ExecutableExceptionableAction<SessionFactory> {

  private SessionFactoryConfig config;

  public SessionFactoryAction(SessionFactoryConfig config) {
    this.config = config;
  }

  @Override
  public SessionFactory run() throws Exception {
    return new SessionFactory(
        this.config.getDriverConfiguration(),
        this.config.getPackages().stream().toArray(String[]::new));
  }
}

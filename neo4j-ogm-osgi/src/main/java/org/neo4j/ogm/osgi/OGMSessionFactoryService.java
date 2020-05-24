package org.neo4j.ogm.osgi;

import org.neo4j.ogm.session.SessionFactory;

public interface OGMSessionFactoryService {
  SessionFactory getSessionFactory() throws Exception;
}

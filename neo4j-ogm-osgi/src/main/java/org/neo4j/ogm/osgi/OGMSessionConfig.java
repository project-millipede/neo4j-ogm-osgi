package org.neo4j.ogm.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "OGM Session Factory Service Configuration",
    description = "Factory Service Configurations")
public @interface OGMSessionConfig {

  @AttributeDefinition(name = "Domain Packages", type = AttributeType.STRING)
  String[] domain_packages();

  @AttributeDefinition(name = "Username", type = AttributeType.STRING)
  String username();

  @AttributeDefinition(name = "Password", type = AttributeType.PASSWORD)
  String password();

  @AttributeDefinition(name = "URI", type = AttributeType.STRING)
  String uri();

}
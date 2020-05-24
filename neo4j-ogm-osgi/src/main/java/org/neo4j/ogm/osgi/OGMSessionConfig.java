package org.neo4j.ogm.osgi;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
    name = "OGM Session Factory Service Configuration",
    description = "Factory Service Configurations")
public @interface OGMSessionConfig {

  @AttributeDefinition(name = "Domain Packages", type = AttributeType.STRING)
  String[] domain_packages() default {"org.neo4j.ogm.demo.osgi.model"};

  @AttributeDefinition(name = "username", type = AttributeType.STRING)
  String username() default "neo4j";

  @AttributeDefinition(name = "password", type = AttributeType.PASSWORD)
  String password() default "neo4jPWD";

  @AttributeDefinition(name = "uri", type = AttributeType.STRING)
  String uri() default "bolt://localhost:7687";
}
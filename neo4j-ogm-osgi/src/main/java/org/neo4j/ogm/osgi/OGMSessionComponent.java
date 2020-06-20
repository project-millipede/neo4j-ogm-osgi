package org.neo4j.ogm.osgi;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.osgi.utils.classloader.ClassLoaderSwitcher;
import org.neo4j.ogm.session.SessionFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;

import java.util.ArrayList;
import java.util.Arrays;

@Designate(ocd = OGMSessionConfig.class)
@Component(
    service = OGMSessionComponentService.class,
    configurationPid = OGMConstants.OGM_SESSION_COMPONENT_CONFIG,
    configurationPolicy = ConfigurationPolicy.REQUIRE
)
public class OGMSessionComponent implements OGMSessionComponentService {

  private OGMSessionConfig config;

  public SessionFactory getSessionFactory() throws Exception {

    Configuration configuration =
        new Configuration.Builder()
            .uri(this.config.uri())
            .credentials(this.config.username(), this.config.password())
            .build();

    SessionFactoryConfig sessionFactoryConfig = new SessionFactoryConfig();
    sessionFactoryConfig.setDriverConfiguration(configuration);
    sessionFactoryConfig.setPackages(new ArrayList<>(Arrays.asList(this.config.domain_packages())));

    return ClassLoaderSwitcher.executeActionOnSpecifiedClassLoader(
        this.getClass().getClassLoader(), new SessionFactoryAction(sessionFactoryConfig));
  }

  @Activate
  @Modified
  protected void activate(final OGMSessionConfig config) {
    this.config = config;
  }
}

# Neo4j OGM for OSGi environments

> Link OSGi-relevant aspects without changing the source code of the original project.

## Requirements to make Neo4j-OGM OSGi compatible

|     Modules     |                               General purpose |                                          OSGi integration |
| :-------------: | --------------------------------------------: | --------------------------------------------------------: |
|       API       |                                               |                                                           |
|      Core       | Domain object scanning - see class DomainInfo |   Important: Ensure the appropriate classloader gets used |
| Embedded-Driver |                                               |                         Fragment Host: org.neo4j.ogm-core |
|   Bolt-Driver   |                                               |                         Fragment Host: org.neo4j.ogm-core |
|   Http-Driver   |                                               |                         Fragment Host: org.neo4j.ogm-core |
|     Feature     |          Generate deployment description (DD) | Launch the project within the OSGi-ecosystem Apache Karaf |

## Git Submodule (Neo4j-OGM)

Integration of original source code into a higher-level build system.

## Extension of the build system by a set of additional instructions

The build of the original project integrated through git submodule has to be extended.
The primary aspect of extending the build system is to provide additional instructions,
so the inner build instructions acknowledge outside adjustments.

### Pre-Build

```
mvn -s settings-modify.xml clean install -f pom-modify.xml
```

The result of this build step is that the base module of the project accepts an external parent element.
Also, some properties might be of interest to change from an outside control.

The pom file of the original project will result

```xml
<project>
...
  <parent>
    <groupId>org.neo4j</groupId>
    <artifactId>neo4j-ogm-osgi</artifactId>
    <version>4.0.0-SNAPSHOT</version>
    <relativePath>../</relativePath>
  </parent>
  <groupId>org.neo4j</groupId>
  <artifactId>neo4j-ogm</artifactId>
  <version>4.0.0-SNAPSHOT</version>
  <packaging>pom</packaging>
...
</project>
```

### Build

#### Default build - include tests

> Requirement: Docker installation on the build system

```
mvn -s settings.xml clean install
```

#### Minified build - excluding tests, and docs

> Without a docker installation

```
mvn -s settings.xml clean install -Dmaven.test.skip=true -Dmaven.javadoc.skip=true
```

## Using artifacts

> The project has a build and deployment pipeline; Gitlab gets used for this purpose.

### Learn more about the CI/CD integrated

To learn more about the build and deployment pipeline, look at the .gitlab-ci.yml file in the
base directory of the project structure.

> Artifact will be deployed to Gitlab`s maven repository if changes happen in the default branch (master).

Other branches, such as features or pull requests, only pass through the build phase, including the execution of tests;
there is no deployment of artifacts into the repository.

### Repository

Using the project`s maven repository in your project

> Note: Neo4j Java Driver repository mentioned is optional.
> Depending on what neo4j-java-driver you are using in your project either integrate this repository (custom build)
> or use maven`s default (maven-central). The artifacts hosted in the repository mentioned here use the latest
> java driver version from the respective master branch.

```xml
<settings>
...
  <profiles>
     <profile>
        <id>your-profile-id</id>
        <repositories>
           <!-- Neo4j Java Driver - custom build dependency hosted at Gitlab maven registry -->
           <repository>
              <id>gitlab-neo4j-java-driver</id>
              <url>https://gitlab.com/api/v4/projects/18622687/packages/maven</url>
              <releases>
                 <enabled>true</enabled>
              </releases>
              <snapshots>
                 <enabled>true</enabled>
              </snapshots>
           </repository>
           <!-- Neo4j OGM - custom build dependency hosted at Gitlab maven registry -->
           <repository>
              <id>gitlab-neo4j-ogm</id>
              <url>https://gitlab.com/api/v4/projects/18591736/packages/maven</url>
              <releases>
                 <enabled>true</enabled>
              </releases>
              <snapshots>
                 <enabled>true</enabled>
              </snapshots>
           </repository>
        </repositories>
     </profile>
  </profiles>
...
</settings>
```
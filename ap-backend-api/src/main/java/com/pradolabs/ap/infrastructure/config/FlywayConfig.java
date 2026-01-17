package com.pradolabs.ap.infrastructure.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(org.springframework.boot.flyway.autoconfigure.FlywayProperties.class)
public class FlywayConfig {

  @Bean
  @ConfigurationProperties("spring.datasource")
  public org.springframework.boot.jdbc.autoconfigure.DataSourceProperties dataSourceProperties() {
    return new org.springframework.boot.jdbc.autoconfigure.DataSourceProperties();
  }

  @Bean
  public DataSource dataSource(org.springframework.boot.jdbc.autoconfigure.DataSourceProperties properties) {
    return properties.initializeDataSourceBuilder()
        .type(HikariDataSource.class)
        .build();
  }

  @Bean(initMethod = "migrate")
  public Flyway flyway(
      DataSource dataSource,
      org.springframework.boot.flyway.autoconfigure.FlywayProperties flywayProperties) {
    return Flyway.configure()
        .dataSource(dataSource)
        .locations(flywayProperties.getLocations()
            .toArray(new String[0]))
        .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
        .defaultSchema(flywayProperties.getDefaultSchema())
        .load();
  }
}

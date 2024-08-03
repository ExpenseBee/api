package com.expensebee.api.configuration;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {
  @Value("${spring.flyway.url}")
  private String URL;

  @Value("${spring.flyway.user}")
  private String USERNAME;

  @Value("${spring.flyway.password}")
  private String DB_PASSWORD;

  @Bean
  public Flyway flyway() {
    var flyway = Flyway.configure()
      .dataSource(URL, USERNAME, DB_PASSWORD)
      .baselineOnMigrate(true)
      .load();

    flyway.migrate();
    return flyway;
  }
}

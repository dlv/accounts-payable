package com.pradolabs.ap.infrastructure.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

public class DotEnvConfig 
    implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(ConfigurableApplicationContext context) {
    ConfigurableEnvironment environment = context.getEnvironment();
    
    Dotenv dotenv = Dotenv.configure()
        .directory("./src/main/resources")
        .ignoreIfMissing()
        .load();

    Map<String, Object> envMap = new HashMap<>();
    dotenv.entries().forEach(entry -> 
        envMap.put(entry.getKey(), entry.getValue()));

    environment.getPropertySources()
        .addFirst(new MapPropertySource("dotenvProperties", envMap));
  }
}

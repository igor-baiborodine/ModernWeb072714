package com.farata.course.mwd.auction.config;

import com.google.gson.Gson;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Keeps beans configuration.
 */
@Configuration
public class BeansConfig {

  @Bean
  public Gson gson() {
    return new Gson();
  }
}

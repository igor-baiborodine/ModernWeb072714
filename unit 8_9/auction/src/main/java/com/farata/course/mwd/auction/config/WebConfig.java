package com.farata.course.mwd.auction.config;

import com.farata.course.mwd.auction.http.converter.GsonHttpMessageConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.List;

import static org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;


/**
 * Keeps custom Spring MVC configuration.
 */
@Configuration
public class WebConfig extends WebMvcAutoConfigurationAdapter {

  @Autowired
  private BeansConfig beansConfig;

  @Override
  public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
    super.configureMessageConverters(converters);
    converters.add(new GsonHttpMessageConverter(beansConfig.gson()));
  }
}

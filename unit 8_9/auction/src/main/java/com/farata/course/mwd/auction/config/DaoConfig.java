package com.farata.course.mwd.auction.config;

import com.farata.course.mwd.auction.dao.ProductDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


/**
 * Keeps Data Access Layer beans and configuration.
 */
@Configuration
@PropertySource("classpath:app.properties")
public class DaoConfig {

  private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
  private static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";
  private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

  @Autowired
  private Environment env;

  /**
   * Data source for production Auction database.
   */
  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
    dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
    dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
    dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));

    return dataSource;
  }

  /**
   * Creates in-memory data source. Convenient during development time.
   */
  @Bean
  public DataSource embeddedDataSource() {
    EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
    return dbBuilder.setType(EmbeddedDatabaseType.H2)
        .addScript("sql/auction.ddl.sql")
        .addScript("sql/auction.dml.sql").build();
  }

  @Bean
  public ProductDao productDao() {
    return new ProductDao(embeddedDataSource());
  }
}

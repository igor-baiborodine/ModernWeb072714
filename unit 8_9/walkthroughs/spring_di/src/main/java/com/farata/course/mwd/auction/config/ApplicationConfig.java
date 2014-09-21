package com.farata.course.mwd.auction.config;

import com.farata.course.mwd.auction.data.DataEngine;
import com.farata.course.mwd.auction.data.IDataEngine;
import com.farata.course.mwd.auction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * TODO
 *
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 * @since 9/21/14
 */
@Configuration
@PropertySource("classpath:app.properties")
public class ApplicationConfig {

    @Autowired
    Environment env;

    @Bean IDataEngine dataEngine() {
        DataEngine dataEngine = new DataEngine();
        dataEngine.init();
        return dataEngine;
    }

    @Bean ProductService productService() {
        ProductService productService = new ProductService();
        productService.setDataEngine(dataEngine());
        return productService;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}

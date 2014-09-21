package com.farata.course.mwd.auction.test

import com.farata.course.mwd.auction.data.DataEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * TODO
 *
 * @since 9/20/14
 * @author Viktor Gamov (viktor.gamov@faratasystems.com)
 */
@Configuration
@ComponentScan(basePackages = [
        "com.farata.course"
])
class TestConfiguration {

    @Bean
    DataEngine dataEngine(){
        DataEngine dataEngine = new DataEngine();
        dataEngine.init();
        return dataEngine;
    }
}

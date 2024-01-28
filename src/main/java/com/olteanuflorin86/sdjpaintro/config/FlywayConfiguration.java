package com.olteanuflorin86.sdjpaintro.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {

	@Bean
	@ConfigurationProperties("spring.card.flyway")
	public DataSourceProperties cardFlywayDataSourceProps() {
		return new DataSourceProperties();
	}
	
    @Bean
    @ConfigurationProperties("spring.cardholder.flyway")
    public DataSourceProperties cardholderFlywayDataSourceProps(){
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("spring.pan.flyway")
    public DataSourceProperties panFlywayDataSourceProps(){
        return new DataSourceProperties();
    }

}

package com.olteanuflorin86.sdjpaintro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.olteanuflorin86.sdjpaintro.domain.cardholder.CreditCardHolder;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class CardHolderDatabaseConfiguration {

	@Bean
	@ConfigurationProperties("spring.cardholder.datasource")
	public DataSourceProperties cardHolderDataSourceProperties() {
		return new DataSourceProperties();
	}
	
    @Bean
    public DataSource cardholderDataSource(@Qualifier("cardHolderDataSourceProperties") DataSourceProperties cardHolderDataSourceProperties){
        return cardHolderDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory(@Qualifier("cardholderDataSource") DataSource cardholderDataSource, EntityManagerFactoryBuilder builder){
        return builder.dataSource(cardholderDataSource)
                .packages(CreditCardHolder.class)
                .persistenceUnit("cardholder")
                .build();
    }
}

package com.olteanuflorin86.sdjpaintro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.olteanuflorin86.sdjpaintro.domain.pan.CreditCardPAN;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class PanDatabaseConfiguration {

	@Bean
	@Primary
	@ConfigurationProperties("spring.pan.datasource")
	public DataSourceProperties panDataSourceProperties() {
		return new DataSourceProperties();
	}
	
    @Primary
    @Bean
    public DataSource panDataSource(@Qualifier("panDataSourceProperties") DataSourceProperties panDataSourceProperties){
        return panDataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }
    
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean panEntityManagerFactory(@Qualifier("panDataSource") DataSource panDataSource, EntityManagerFactoryBuilder builder){
        return builder.dataSource(panDataSource)
                .packages(CreditCardPAN.class)
                .persistenceUnit("pan")
                .build();
    }
    
    @Primary
    @Bean
    public PlatformTransactionManager panTransactionManager(
            @Qualifier("panEntityManagerFactory") LocalContainerEntityManagerFactoryBean panEntityManagerFactory){
        return new JpaTransactionManager(panEntityManagerFactory.getObject());
    }
}

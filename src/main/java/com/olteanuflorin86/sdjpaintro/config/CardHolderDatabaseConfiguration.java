package com.olteanuflorin86.sdjpaintro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.olteanuflorin86.sdjpaintro.domain.cardholder.CreditCardHolder;
import com.zaxxer.hikari.HikariDataSource;

@EnableJpaRepositories(basePackages = "com.olteanuflorin86.sdjpaintro.repositories.cardholder",
entityManagerFactoryRef = "cardholderEntityManagerFactory", transactionManagerRef = "cardholderTransactionManager")
@Configuration
public class CardHolderDatabaseConfiguration {

	@Bean
	@ConfigurationProperties("spring.cardholder.datasource")
	public DataSourceProperties cardHolderDataSourceProperties() {
		return new DataSourceProperties();
	}
	
    @Bean
    @ConfigurationProperties("spring.cardholder.datasource.hikari")
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

    @Bean
    public PlatformTransactionManager cardholderTransactionManager(
            @Qualifier("cardholderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardholderEntityManagerFactory){

        return new JpaTransactionManager(cardholderEntityManagerFactory.getObject());
    }

}

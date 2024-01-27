package com.olteanuflorin86.sdjpaintro.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.olteanuflorin86.sdjpaintro.domain.creditcard.CreditCard;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class CardDatabaseConfiguration {

	@Bean
	@ConfigurationProperties("spring.card.datasource")
	public DataSourceProperties cardDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	public DataSource cardDataSource(@Qualifier("cardDataSourceProperties") DataSourceProperties cardDataSourceProperties) {
		return cardDataSourceProperties.initializeDataSourceBuilder()
				.type(HikariDataSource.class)
				.build();
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(@Qualifier("cardDataSource") DataSource cardDataSource, EntityManagerFactoryBuilder builder) {
		return builder.dataSource(cardDataSource)
				.packages(CreditCard.class)
				.persistenceUnit("card")
				.build();
	}
}

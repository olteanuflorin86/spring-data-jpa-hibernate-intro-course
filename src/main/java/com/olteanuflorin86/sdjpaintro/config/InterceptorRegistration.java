package com.olteanuflorin86.sdjpaintro.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.context.annotation.Configuration;

import com.olteanuflorin86.sdjpaintro.interceptors.EncryptionInterceptor;

//@Configuration
public class InterceptorRegistration implements HibernatePropertiesCustomizer {

//	@Autowired
//	EncryptionInterceptor interceptor;
	
	@Override
	public void customize(Map<String, Object> hibernateProperties) {

//		hibernateProperties.put("hibernate.session_factory.interceptor", interceptor);
		
	}

}

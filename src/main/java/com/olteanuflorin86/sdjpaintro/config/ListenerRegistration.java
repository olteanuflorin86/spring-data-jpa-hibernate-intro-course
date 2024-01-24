package com.olteanuflorin86.sdjpaintro.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.stereotype.Component;

import com.olteanuflorin86.sdjpaintro.listeners.PostLoadListener;
import com.olteanuflorin86.sdjpaintro.listeners.PreInsertListener;
import com.olteanuflorin86.sdjpaintro.listeners.PreUpdateListener;

//@Component
public class ListenerRegistration implements BeanPostProcessor {

    private final PostLoadListener postLoadListener;
    private final PreInsertListener preInsertListener;
    private final PreUpdateListener preUpdateListener;
    
    public ListenerRegistration(PostLoadListener postLoadListener, PreInsertListener preInsertListener, PreUpdateListener preUpdateListener) {
        this.postLoadListener = postLoadListener;
        this.preInsertListener = preInsertListener;
        this.preUpdateListener = preUpdateListener;
    }

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		// TODO Auto-generated method stub
//		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
		
//        if (bean instanceof LocalContainerEntityManagerFactoryBean){
//            LocalContainerEntityManagerFactoryBean lemf = (LocalContainerEntityManagerFactoryBean) bean;
//            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lemf.getNativeEntityManagerFactory();
//            EventListenerRegistry registry = sessionFactory.getServiceRegistry()
//                    .getService(EventListenerRegistry.class);
// 
//            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
//            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
//            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
//        }

        return bean;
	}
    
}

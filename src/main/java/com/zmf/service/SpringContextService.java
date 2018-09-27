package com.zmf.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component("springContextService")
public class SpringContextService implements ApplicationContextAware {
    @Autowired
    private static ApplicationContext applicationContext;

    /**
     * get a bean by the bean name
     *
     * @param beanName the name of the bean
     * @return the bean instance
     */
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    /**
     * get the application context
     *
     * @return the application context
     */
    public static ApplicationContext getContext() {
        return applicationContext;
    }

    public void setApplicationContext(
            ApplicationContext applicationContext) throws BeansException {
        SpringContextService.applicationContext = applicationContext;
    }
}

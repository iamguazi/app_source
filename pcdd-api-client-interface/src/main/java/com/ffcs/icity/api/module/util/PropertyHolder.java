package com.ffcs.icity.api.module.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class PropertyHolder implements ApplicationContextAware{

    private static Property property;
    

    public static Property getProperty() {
        return property;
    }

    public static void setProperty(Property property) {
        PropertyHolder.property = property;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        PropertyHolder.property = applicationContext.getBean(Property.class);
    }

}

package com.dfs.freemarkerDemo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

@Configuration
@Profile("dev")
public class FreeMarkerConfig implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String baenName) {
        if (bean instanceof FreeMarkerConfigurer) {
            var configurer = (FreeMarkerConfigurer)bean;
            Map<String, Object> sharedVariables = new HashMap<>();
            // Set dev mode to load React dev app
            sharedVariables.put("dev", true);
            configurer.setFreemarkerVariables(sharedVariables);
        }

        return bean;
    }
}

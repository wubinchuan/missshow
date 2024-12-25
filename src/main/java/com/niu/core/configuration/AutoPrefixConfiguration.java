package com.niu.core.configuration;

import com.niu.core.hack.AutoPrefixUrlMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
@Component
public class AutoPrefixConfiguration implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        //一种机制是使用特定的注解 @ControllerAdive
        //现在这种发现机制@Component+实现特定的类
        return new AutoPrefixUrlMapping();
    }
}

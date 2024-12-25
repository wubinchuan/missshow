package com.niu.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@PropertySource(value = "classpath:config/exception-code.properties")
@ConfigurationProperties(prefix = "lin")
@Component
public class ExceptionCodeConfiguration {
    public Map<Integer, String> getCodes() {
        return codes;
    }

    public  String getMessage(int code){
        String message=codes.get(code);
        return message;
    }

    public void setCodes(Map<Integer, String> codes) {
        this.codes = codes;
    }

    private Map<Integer,String> codes=new HashMap<>();
}

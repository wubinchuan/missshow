package com.niu.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
//注册在AutoPrefixConfiguration
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {
    @Value("${missyou.api-package}")
    private String apiPackagePath;
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo= super.getMappingForMethod(method, handlerType);

        if(mappingInfo != null){
            String prefix=this.getPrefix(handlerType);
            System.out.println(prefix);
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return mappingInfo;
    }
    private String getPrefix(Class<?> handlerType){
        String PackagName=handlerType.getPackage().getName();
        String dotPath=PackagName.replaceAll(this.apiPackagePath,"");
        return dotPath.replace(".","/");
    }
}

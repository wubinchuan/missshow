package com.niu.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = PasswordValidator.class)
public @interface PasswordEqual {
    //注意注解内只能使用基本类型

    String message() default "password are not equal";
    Class<?>[] group() default  {};

    Class<? extends Payload>[] Payload() default {};

}

package com.niu.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordEqual,String> {
    private int max;
    private int min;
    @Override
    public void initialize(PasswordEqual constraintAnnotation) {
//        this.max=constraintAnnotation.max()
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    //    第二参数是目标的类型,比如搭载login这个dto，第二参数就是login类
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //获取传参

        String password1=s;
        String password2="zz";
        return password2.equals(password1);
    }

}

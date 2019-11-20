package com.nt.demo.provider.service;

import com.nt.demo.middle.annotations.Verify;
import com.nt.demo.middle.exception.MyException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

/**
 * @author  by TaoTaoNing
 * 2019-11-19
 **/
//@Slf4j
//@Component
//@Aspect
public class ParametersVerifyAop {

    @Before(value = "@annotation(com.nt.demo.middle.annotations.Verifys)")
    public void parameterVerify(JoinPoint point)throws IllegalAccessException{
        System.out.println("before ----------- do ");
        //切点对象
        Object obj = point.getArgs()[0];
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //需要做校验的参数
            if (field.isAnnotationPresent(Verify.class)) {
                Verify verify = field.getAnnotation(Verify.class);
                String name = verify.name();
                int maxLength = verify.maxLength();
                int minLength = verify.minLength();
                boolean required = verify.required();
                String regular = verify.regular();
                //属性值
                Object fieldObj = field.get(obj);
                if (required) {
                    if (fieldObj == null) {
                        throw new MyException("00",String.format("【%s】为必填参数", name));
                    }
                }
                if (Integer.MAX_VALUE != maxLength) {
                    if (maxLength < String.valueOf(fieldObj).length()) {
                        throw  new MyException("00",String.format("【%s】长度不合理，最大长度为【%s】", name, maxLength));
                    }
                }
                if (Integer.MIN_VALUE != minLength) {
                    if (minLength > String.valueOf(fieldObj).length()) {
                        throw new MyException("00",String.format("【%s】长度不合理，最小长度为【%s】", name, maxLength));
                    }
                }
                if (!"".equals(regular)) {
                    Pattern pattern = Pattern.compile(regular);
                    if (!pattern.matcher(String.valueOf(fieldObj)).matches()) {
                        throw new MyException("00",String.format("参数【%s】的请求数据不符合规则", name));
                    }
                }
            }
        }
    }

}

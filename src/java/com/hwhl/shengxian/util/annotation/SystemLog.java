package com.hwhl.shengxian.util.annotation;

import java.lang.annotation.*;

/**
 * Description: 自定义注解
 *
 * @Author: yang
 * @Date: 2018/7/26
 * @Version: 1.0
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String module()  default "";
    String methods()  default "";
}

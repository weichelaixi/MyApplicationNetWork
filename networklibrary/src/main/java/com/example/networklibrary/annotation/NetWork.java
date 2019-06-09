package com.example.networklibrary.annotation;

import com.example.networklibrary.type.NetType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: MyApplication
 * @Package: com.example.networklibrary.annotation
 * @ClassName: NetWork
 * @Description: java类作用描述
 * @Author: 车伟
 * @CreateDate: 2019/4/13 22:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/4/13 22:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Target(ElementType.METHOD) //目标在方法之上
@Retention(RetentionPolicy.RUNTIME) //jvm运行时通过反射获取
public @interface NetWork {
    NetType netType() default NetType.AUTO;
}

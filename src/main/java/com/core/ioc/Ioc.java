package com.core.ioc;

import com.core.BeanContainer;
import com.core.ioc.annotation.Autowired;
import com.core.test.DoodleServiceImpl;
import com.core.utils.ClassUtil;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * @DESC
 * @AUTHOR zzf
 * @DATA 2021/6/1 13:15
 */
public class Ioc {
    private BeanContainer beanContainer;

    public Ioc(){
        beanContainer = BeanContainer.getInstance();
    }

    public void doIoc(){
        //遍历所有容器内class
        for(Class<?> clz : beanContainer.getClasses()){

            //获取类所对应的的实例
            final Object targetBean = beanContainer.getBean(clz);
//            获取所有字段
            Field[] fields=clz.getDeclaredFields();
//            遍历每个字段，判断是否有AutoWired注解
            for(Field field: fields){
                if(field.isAnnotationPresent(Autowired.class)){
                    final Class<?> fieldClass = field.getType();

                    Object fieldValue = getInstanceClass(fieldClass);
//                    System.out.println(field.getType().toString());
//                    System.out.println(fieldClass.toString());
//                    System.out.println(fieldValue.toString());
//                    System.out.println(fieldValue.toString());
                    if (null != fieldValue) {
                        ClassUtil.setField(field, targetBean, fieldValue);
                    } else {
                        throw new RuntimeException("无法注入对应的类，目标类型:" + fieldClass.getName());
                    }
                }
            }
        }
    }



    private Object getInstanceClass(final Class<?> clz) {
//        System.out.println(clz.toString());
        return Optional
                .ofNullable(beanContainer.getBean(clz))
                .orElseGet(
                        ()->{
//                            System.out.println("----error----");
                            Class<?> implementClass=getImplementClass(clz);
                            if(null!=implementClass){
                                return beanContainer.getBean(implementClass);
                            }
                            return null;
                        }
                );
    }

    private Class<?> getImplementClass(Class<?> clz) {
        return beanContainer.getClassesBySuper(clz)
                .stream()
                .findFirst()
                .orElse(null);
    }
}

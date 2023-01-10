package com.example.iocexample.core;

import com.example.iocexample.annotation.Autowired;
import com.example.iocexample.annotation.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IOCContext {
    private static final Map<Class<?>, Object> BEAN_LIST = new HashMap<>();

    static {
        Set<Class<?>> classes = new Reflections("com.example.iocexample").getTypesAnnotatedWith(Component.class);
        classes.forEach(aClass -> {
            for (Constructor<?> constructor : aClass.getConstructors()) {
//                System.out.println("constructor = " + constructor);
                try {
                    Object newInstance = constructor.newInstance();
                    BEAN_LIST.put(aClass, newInstance);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        assignFields();
    }

    private static void assignFields() {
        BEAN_LIST.forEach((aClass, o) -> {
            for (Field declaredField : aClass.getDeclaredFields()) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    try {
                        Object bean = getBean(declaredField.getType());
                        declaredField.setAccessible(true);
                        declaredField.set(o,bean );
                        declaredField.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    public static <T> T getBean(Class<T> aClass) {
        T t = (T) BEAN_LIST.get(aClass);
        return t;
    }
}

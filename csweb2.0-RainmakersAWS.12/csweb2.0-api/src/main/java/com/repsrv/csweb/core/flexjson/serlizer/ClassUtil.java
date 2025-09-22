package com.repsrv.csweb.core.flexjson.serlizer;

import java.util.Set;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassUtil{

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    public static Class<?> getAttributeClass(String path, String name, Class<?> root){
        Type current = root;
        String[] properties = path.split("\\.");
        for(String property : properties)
            if(!property.isEmpty())
                current = getType(current, property);

        return (Class<?>) current;
    }

    private static Type getType(Type current, String property){
        Type result;

        Field field = FieldUtils.getField(((Class<?>) current), property, true);
        if(field != null)
            result = field.getGenericType(); // Field belongs to the class.
        else // Let's try a get.
            result = findField(current, property);

        if(result instanceof ParameterizedType)
            result = extractGenericType(result);

        return result;
    }

    private static Type findField(Type current, String property){
        Type result;
        try{
            String methodName = "get" + capitalize(property);
            result = MethodUtils.getAccessibleMethod(((Class<?>) current), methodName).getReturnType();
        }catch(Exception exception){
            System.out.println(current.getTypeName()+" get property: "+property);
            result = querySubclasses(current, property);
        }

        return result;
    }

    private static Type querySubclasses(Type current, String property){
        Type result = current;
        String packageToScan = ((Class<?>) current).getPackage().getName();
        Reflections reflections = new Reflections(packageToScan);
        Set<?> subTypes = reflections.getSubTypesOf((Class<?>) current);

        while(subTypes.iterator().hasNext()){
            Object clazz = subTypes.iterator().next();
            Class<?> classType = (Class<?>) clazz;
            result = getType(classType, property);
            if(result != null) break;
        }

        return result;
    }

    private static Type extractGenericType(Type result){
        ParameterizedType type = (ParameterizedType) result;
        Type[] typeArguments = type.getActualTypeArguments();
        for(Type typeArgument : typeArguments){
            result = typeArgument;
        }

        return result;
    }

    private static String capitalize(String str) {
        if(str == null || str.isEmpty()) {
            return str;
        }
        return StringUtils.capitalize(str);
        
    }
}

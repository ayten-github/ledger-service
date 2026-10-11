package com.ledger.service.validation;


import com.ledger.service.command.dao.entity.BaseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class UpdateUtils {


    public BaseEntity update(BaseEntity entity, String[] property, Object... value) throws Exception {
        if (property.length != value.length) {
            throw new IllegalArgumentException("Properties and values must have the same length");
        }

        for (int i = 0; i < property.length; i++) {
            String propertyName = property[i];
            Object valueObj = value[i];

            String setterName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);

            Method setterMethod = null;

            try {
                setterMethod = entity.getClass().getMethod(setterName, valueObj.getClass());
            } catch (NoSuchMethodException e) {
                for (Method m : entity.getClass().getMethods()) {
                    if (m.getName().equals(setterName) && m.getParameterCount() == 1) {
                        setterMethod = m;
                        break;
                    }
                }
                if (setterMethod == null) {
                    throw new NoSuchMethodException("Setter not found for property: " + propertyName);
                }
            }

            Class<?> paramType = setterMethod.getParameterTypes()[0];
            Object convertedValue = convertValue(valueObj, paramType);

            setterMethod.invoke(entity, convertedValue);
        }

        return entity;
    }
}
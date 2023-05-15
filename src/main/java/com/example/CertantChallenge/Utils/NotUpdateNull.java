package com.example.CertantChallenge.Utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Objects;

public final class NotUpdateNull {
    // Retorna los nombres de los atributos que tienen valor null
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source); // BeanWrapper es una clase que permite acceder a los atributos de un objeto y a su metadatos(nombre, tipo, etc)

        Arrays.stream(src.getPropertyDescriptors()).filter(propertyDescriptor -> src.getPropertyValue(propertyDescriptor.getName()) instanceof java.util.List).forEach(propertyDescriptor -> {
            try {
                if (((java.util.List<?>) Objects.requireNonNull(src.getPropertyValue(propertyDescriptor.getName()))).size() == 0) {
                    src.setPropertyValue(propertyDescriptor.getName(), null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }); // Si la propiedad es una lista y esta vacia, se setea a null

        //Retorno una matriz de String con los nombres de los atributos que tienen valor null
        return Arrays.stream(src.getPropertyDescriptors()).map(PropertyDescriptor::getName).filter(name -> src.getPropertyValue(name) == null).toArray(String[]::new);
    }

}

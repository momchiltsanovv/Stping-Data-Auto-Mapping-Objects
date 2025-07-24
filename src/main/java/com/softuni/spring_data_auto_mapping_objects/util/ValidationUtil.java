package com.softuni.spring_data_auto_mapping_objects.util;


import jakarta.validation.ConstraintViolation;

import java.util.Set;

public interface ValidationUtil {

    <E> Set<ConstraintViolation<E>> violation(E entity);

    <E> boolean isValid(E entity);



}

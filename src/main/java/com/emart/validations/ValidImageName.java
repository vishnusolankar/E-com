package com.emart.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy. RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ValidImageName{
    //error message
    String message() default "Invalid image name";

    //represent group of constraints
    Class<?>[] groups() default { };

    //additional info about anno
    Class<? extends Payload>[] payload() default { };

    String[] allowedExtensions() default {".jpg",".png",".jpeg"};
}

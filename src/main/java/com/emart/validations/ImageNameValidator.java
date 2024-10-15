package com.emart.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class ImageNameValidator implements ConstraintValidator<ValidImageName,String> {

    private static Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);

    private String[] allowedExtensions;

    @Override
    public void initialize(ValidImageName constraintAnnotation) {
        this.allowedExtensions = constraintAnnotation.allowedExtensions();
    }

    @Override
    public boolean isValid(String imageName, ConstraintValidatorContext context) {
        logger.info("Checking custom validation for image name : {} ", imageName);
        if(imageName==null || imageName.isEmpty())
            return false;
        else
            return Arrays.stream(allowedExtensions)
                    .anyMatch(imageName.toLowerCase()::endsWith);
    }
}

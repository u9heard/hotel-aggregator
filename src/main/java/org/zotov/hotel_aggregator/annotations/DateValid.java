package org.zotov.hotel_aggregator.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.zotov.hotel_aggregator.validators.DateIntervalValidator;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateIntervalValidator.class)
public @interface DateValid {
    String message() default "Check date interval";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String dateStartMethod();

    String dateEndMethod();
}

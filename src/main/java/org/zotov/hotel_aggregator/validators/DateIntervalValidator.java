package org.zotov.hotel_aggregator.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.zotov.hotel_aggregator.annotations.DateValid;
import org.zotov.hotel_aggregator.dto.reservation.ReservationRequestDTO;

import javax.swing.*;
import java.lang.reflect.Method;
import java.time.LocalDate;

public class DateIntervalValidator implements ConstraintValidator<DateValid, Object> {

    private String startDateMethodName;
    private String endDateMethodName;

    @Override
    public void initialize(DateValid constraintAnnotation) {
        this.startDateMethodName = constraintAnnotation.dateStartMethod();
        this.endDateMethodName = constraintAnnotation.dateEndMethod();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Method startDateMethod = o.getClass().getMethod(startDateMethodName);
            Method endDateMethod = o.getClass().getMethod(endDateMethodName);

            LocalDate dateStart = (LocalDate) startDateMethod.invoke(o);
            LocalDate dateEnd = (LocalDate) endDateMethod.invoke(o);

            if(dateStart.isEqual(dateEnd)){
                return false;
            }
            if (dateStart.isBefore(LocalDate.now()) || dateEnd.isBefore(LocalDate.now())) {
                return false;
            }
            return !dateStart.isAfter(dateEnd);
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

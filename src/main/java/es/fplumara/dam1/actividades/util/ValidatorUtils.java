package es.fplumara.dam1.actividades.util;
import es.fplumara.dam1.actividades.exception.ObjectValidationError;
import jakarta.validation.*;

import java.util.List;
import java.util.Set;
public class ValidatorUtils {

        private static final ValidatorFactory FACTORY = Validation.buildDefaultValidatorFactory();
        private static final Validator VALIDATOR = FACTORY.getValidator();


        public  static <T> void validateEntity(T obj){
            Set<ConstraintViolation<T>> violations = VALIDATOR.validate(obj);
            if (!violations.isEmpty()) {
                List<String> errors = violations.stream()
                        .map(v ->v.getPropertyPath().toString() + ": "+v.getMessage())
                        .toList();


                throw new ObjectValidationError(errors);
            }
        }
    }

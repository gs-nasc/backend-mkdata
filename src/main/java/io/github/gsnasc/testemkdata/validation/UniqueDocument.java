package io.github.gsnasc.testemkdata.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueDocumentValidator.class})
public @interface UniqueDocument {

    String message() default "Documento já cadastrado";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

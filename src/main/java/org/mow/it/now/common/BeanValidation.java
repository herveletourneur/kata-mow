package org.mow.it.now.common;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public class BeanValidation {

    private BeanValidation() {
    }

    public static BeanValidation begin() {
        return new BeanValidation();
    }

    public BeanValidation guardNonNull(Object o, String message) {
        return guard(() -> Objects.nonNull(o), message);
    }

    public BeanValidation guard(BooleanSupplier checker, String message) {
        if (checker.getAsBoolean()) {
            return this;
        }
        throw new BeanValidationException(message);
    }
}

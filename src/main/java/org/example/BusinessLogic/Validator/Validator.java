package org.example.BusinessLogic.Validator;

public interface Validator<T> {

    /**
     * Validate.
     *
     * @param t the t
     */
    void validate(T t) throws Exception;
}
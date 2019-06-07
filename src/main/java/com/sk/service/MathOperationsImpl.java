package com.sk.service;

import com.sk.service.interfaces.IMathOperations;

    //make MathOperationsImpl class which implement IMathOperations and declarate what this class do

public class MathOperationsImpl implements IMathOperations {
    //calculateResult gets info from boolean(radiobuttons) and entered value in valueField as string and parse to double

    public double calculateResult(boolean multiply, double value) {
        double tax = 1.23;

        if (multiply) return multiply(value, tax);
        else return divide(value, tax);
    }
    /**
     *  Make multiply
     * @param firstValue - first factor
     * @param secondValue - second factor,tax
     *
     * @return floating point number
     */
    public double multiply(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    /**
     * Make divide
     * @param firstValue - divident
     * @param secondValue - divider,tax
     *
     * @return floating point number
     */
    public double divide(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }
}




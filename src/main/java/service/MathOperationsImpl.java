package service;

import service.interfaces.IMathOperations;

//stworzenie klasy implementujacej IMathOperations i deklarujacej co ma wykonywac
public class MathOperationsImpl implements IMathOperations {
    //calculateResult pobiera informacje ze zmiennej boolean(radiobuttony) oraz wpisana liczbe(tutaj jako string) do valuefielda

    protected double calculateResult(boolean multiply, String text) {
        //todo do stałej
        double tax = 1.23;
        double value = Double.parseDouble(text);

        return multiply ? multiply(value, tax) : divide(value, tax);
    }

    //deklaracje co maja wykonywac funkcje ktore zostaly zaimplementowane

    /**
     *  Wykonanie operacji mnożenia
     * @param firstValue
     * @param secondValue
     *
     * @return liczbe zmiennocyfrową
     */
    public double multiply(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    /**
     * Wykonanie operacji dzielenia
     * @param firstValue dzielna
     * @param secondValue dzielnik
     *
     * @return liczba zmiennoprzecinkowa
     */
    public double divide(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }
}




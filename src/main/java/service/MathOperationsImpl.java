package service;

import interfaces.IMathOperations;
//stworzenie klasy implementujacej IMathOperations i deklarujacej co ma wykonywac
public class MathOperationsImpl implements IMathOperations {
        //calculateResult pobiera informacje ze zmiennej boolean(radiobuttony) oraz wpisana liczbe(tutaj jako string) do valuefielda
    protected double calculateResult(boolean multiply, String text){
        double result;
        double tax = 1.23;
//wrzucenie if'a ktory w zaleznosci od wyboru radiobuttona wskazuje jakie dzialanie ma byc wykonane
       if (multiply) {

//           result = Double.parseDouble(text) * tax;
           result = multiply(Double.parseDouble(text), tax);
       } else {
//           result = Double.parseDouble(text) / tax;
           result = divide(Double.parseDouble(text), tax);
       }
       return result;
       //zwrócić wynik operacji
   }
    //deklaracje co maja wykonywac funkcje ktore zostaly zaimplementowane
    public double multiply(double firstValue, double secondValue) {
        return firstValue * secondValue;
    }

    public double divide(double firstValue, double secondValue) {
        return firstValue / secondValue;
    }
}




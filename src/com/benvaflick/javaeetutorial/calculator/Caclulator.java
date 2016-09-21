package com.benvaflick.javaeetutorial.calculator;

public class Caclulator {

    public static String calculate(int firstOperand, int secondOperand, String operation) {
        int result = 0;
        switch (operation) {
            case "add":
                result = firstOperand + secondOperand;
                operation = " + ";
                break;
            case "divide":
                result = firstOperand / secondOperand;
                operation = " / ";
                break;
            case "multiply":
                result = firstOperand * secondOperand;
                operation = " * ";
                break;
            case "subtract":
                result = firstOperand - secondOperand;
                operation = " - ";
                break;
        }
        return firstOperand + operation + secondOperand + " = " + result;
    }
}

package com.example.calculator;

public class Divide {
    int result;
    int remainder;

    void calculate(int firstValue,int secondValue){
        result = firstValue/secondValue;
        remainder = firstValue%secondValue;
    }

}

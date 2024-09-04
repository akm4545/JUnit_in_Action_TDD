package com.manning.junitbook.ch01;

// Calculator 클래스를 간단하게 테스트할 수 있는 클래스
public class CalculatorTest {
    public static void main(String[] args){
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);
        
        if(result != 60){
            System.out.println("Bad result: " + result);
        }
    }
}

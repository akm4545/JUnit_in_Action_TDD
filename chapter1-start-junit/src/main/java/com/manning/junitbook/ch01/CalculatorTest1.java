package com.manning.junitbook.ch01;

// 오류 메시지는 사람이 눈으로 확인해야 하는 것이다
//그러면 코드를 테스트하는 것이 아니라 코드를 테스트하는 개발자의 능력을 테스트 하는 것이 된다

//자바에서 오류를 알려 주는 일반적인 방법은 예외를 던지는 것이다
//테스트가 실패하면 예외를 던지게 하자

//Calcuator 클래스는 아직 장성하지 않은 다른 메서드도 테스트해야 한다
//프로그램을 모듈식으로 설계하면 예외를 더 쉽게 캐치하고 처리할 수 있다
//나중에 테스트 프로그램을 확장하기도 더 쉬울 것이다
public class CalculatorTest1 {
    private int nbErrors = 0;

//    테스트 로직을 testAdd 메서드로 옮겨 테스트가 수행하는 작업에만 문제를 한정할 수 있으며
//    main 메서드를 유지 보수하기 쉽게 만들면서도 나중에 더 많은 단위 테스트를 추가할 수 있다
    public void testAdd(){
        Calculator calculator = new Calculator();
        double result = calculator.add(10, 50);

        if(result != 60){
            throw new IllegalStateException("Bad result: " + result);
        }
    }

    public static void main(String[] args){
        CalculatorTest1 test = new CalculatorTest1();

//        오류가 발생할 때 스택추적을 출력하도록 변경
//        오류가 있는 경우 예외를 던진다
        try{
            test.testAdd();
        }catch (Throwable e){
            test.nbErrors++;
            e.printStackTrace();
        }

        if(test.nbErrors > 0){
            throw new IllegalStateException("There were " + test.nbErrors + " error(s)");
        }
    }
}

//애플리케이션이 복잡해지고 테스트가 애플리케이션에 점차적으로 개입하게 되면
//이렇게 사용자 정의 테스트 프레임워크를 계속 만들어 나가고 보수하는 일이 개발자에게 부담이 될 수 있다

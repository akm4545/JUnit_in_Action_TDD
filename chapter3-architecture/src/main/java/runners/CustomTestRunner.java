package runners;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CustomTestRunner extends Runner {

//    사용자 정의 runnre에서 테스트 대상 클래스에 대한 참조를 갖기 위한 변수
    private Class<?> testedClass;

    public CustomTestRunner(Class<?> testedClass){
        this.testedClass = testedClass;
    }

//    메서드 재정의
//    getDescription 메서드는 다양한 곳에서 사용할 수 있는 Description 객체를 반환
    @Override
    public Description getDescription(){
        return Description
                .createTestDescription(testedClass, this.getClass().getSimpleName() + " description");
    }

//    run 메서드 재정의
    @Override
    public void run(RunNotifier notifier){
        System.out.println("Running test with " + this.getClass().getSimpleName() + ": " + testedClass);

        try{
//            testedClass 변수를 인스턴스화
            Object testObject = testedClass.newInstance();

//            public 메서드 조회
            for(Method method : testedClass.getMethods()){
//                @Test어노테이션이 달린 메서드 추출
                if(method.isAnnotationPresent(Test.class)){
//                    fireTestStarted 메서드를 호출하여 리스너에게 원자적(atomic) 테스트가 곧 시작한다고 알림
                    notifier.fireTestStarted(Description.createTestDescription(testedClass, method.getName()));
//                    메서드 호출
                    method.invoke(testObject);
//                    fireTestFinished 메서드를 호출하여 리스너에게 원자적 테스트가 완료됨을 알림
                    notifier.fireTestFinished(Description.createTestDescription(testedClass, method.getName()));
                }
            }
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

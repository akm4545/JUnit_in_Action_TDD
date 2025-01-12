package phase3.airport.annotations;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

//어노테이션 생성
@Qualifier
//어노테이션이 런타임에도 남아 있도록 함
@Retention(RUNTIME)
//필드와 메서드에 적용할 수 있다
@Target({FIELD, METHOD})
//어노테이션 생성
public @interface FlightNumber {
//    어노테이션 사용 시 number 파라미터를 지정해야 한다
    String number();
}

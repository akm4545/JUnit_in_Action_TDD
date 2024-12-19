package com.example.chapter18rest;

import com.example.chapter18rest.beans.FlightBuilder;
import com.example.chapter18rest.model.Country;
import com.example.chapter18rest.model.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import java.util.Map;

@SpringBootApplication
//countriesMap 빈을 가지고 있는 FlightBuilder를 가져온다
@Import(FlightBuilder.class)
public class Chapter18RestApplication {

    @Autowired
    private Map<String, Country> countriesMap;

    public static void main(String[] args) {
        SpringApplication.run(Chapter18RestApplication.class, args);
    }

//    CommandLineRunner 타입의 빈을 생성
//    CommandLineRunner 인터페이스는 문자열 배열을 파라미터로 전달하여 커맨드라인 인자에 접근할 수 있도록 해 주는 함수형 인터페이스  
//    (한 개의 추상 메서드만을 갖는 인터페이스)다
//    CommandLineRunner 인터페이스는 스프링 부트 애플리케이션이 실행되는 시점에 실행해야 하는 로직이 있을 때 사용한다
//    run 메서드가 시작하기 바로 직전에 만들어지고 람다식 안의 로직을 수행한다
    @Bean
    CommandLineRunner configureRepository (CountryRepository countryRepository){
        return args -> {
            for (Country country : countriesMap.values()){
                countryRepository.save(country);
            }
        };
    }
}

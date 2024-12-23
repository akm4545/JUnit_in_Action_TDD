package com.example.chapter18rest;

import com.example.chapter18rest.beans.FlightBuilder;
import com.example.chapter18rest.exceptions.PassengerNotFoundException;
import com.example.chapter18rest.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//테스트 클래스의 빈 스캔을 위해 추가
@SpringBootTest

//MockMvc 객체와 관련한 모든 자동 구성을 활성화
@AutoConfigureMockMvc
@Import(FlightBuilder.class)
public class RestApplicationTest {

//    MockMvc는 주로 서버 기능을 테스트하기 위해 사용하는데 여기서는 REST API를 실행
    @Autowired
    private MockMvc mvc;

    @Autowired
    private Flight flight;

    @Autowired
    private Map<String, Country> countriesMap;

//  스프링 애플리케이션 콘텍스트에 모의 객체를 추가하는 데 사용
//    이렇게 추가한 모의 객체는 스프링 애플리케이션 콘텍스트에서 동일한 타입의 기존 빈을 대체한다 (DB에 직접 쓸 순 없으므로?)
//    테스트 중에 모의 객체가 어떻게 동작해야 하는지를 프로그래밍 할 수 있다
    @MockBean
    private PassengerRepository passengerRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    void testGetAllCountries() throws Exception{
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));

//        요청 모사
        mvc.perform(get("/countries"))
//                반환값에 대한 검증
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

//        정확히 한 번만 실행되었는지 검증
        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPassengers() throws Exception{
        when(passengerRepository.findAll()).thenReturn(new ArrayList<>(flight.getPassengers()));

        mvc.perform(get("/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(20)));

        verify(passengerRepository, times(1)).findAll();
    }

    @Test
    void testPassengerNotFound() {
//        예외 검증
        Throwable throwable = assertThrows(
                NestedServletException.class,
                () -> mvc.perform(get("/passengers/30")).andExpect(status().isNotFound())
        );

        assertEquals(PassengerNotFoundException.class, throwable.getCause().getClass());
    }

    @Test
    void testPostPassenger() throws Exception {
        Passenger passenger = new Passenger("Peter Michelsen");
        passenger.setCountry(countriesMap.get("US"));
        passenger.setIsRegistered(false);

        when(passengerRepository.save(passenger)).thenReturn(passenger);

        mvc.perform(post("/passengers")
                .content(new ObjectMapper().writeValueAsString(passenger))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Peter Michelsen")))
                .andExpect(jsonPath("$.country.codeName", is("US")))
                .andExpect(jsonPath("$.country.name", is("USA")))
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));

        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    void testPatchPassenger() throws Exception{
        Passenger passenger = new Passenger("Sophia Graham");
        passenger.setCountry(countriesMap.get("UK"));
        passenger.setIsRegistered(false);

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(passenger)).thenReturn(passenger);

        String updates = "{\"name\":\"Sophia Jones\", \"country\":\"AU\", \"isRegistered\":\"true\"}";

        mvc.perform(patch("/passengers/1")
                .content(updates)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).findById(1L);
        verify(passengerRepository, times(1)).save(passenger);
    }

    @Test
    public void testDeletePassenger() throws Exception {
        mvc.perform(delete("/passengers/4"))
                .andExpect(status().isOk());

        verify(passengerRepository, times(1)).deleteById(4L);
    }
}

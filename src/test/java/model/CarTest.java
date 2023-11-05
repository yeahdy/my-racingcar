package model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarTest {

    Car car;

    @BeforeEach
    void init(){
        car = new Car();
    }


    @Test
    @DisplayName("랜덤 수는 0 ~ 9 사이에 무작위로 반환")
    void getRandomNumberTest(){
        assertThat(car.getRandomNumber()).isBetween(0,9);
    }

    @ParameterizedTest
    @ValueSource(ints = {4,5,9})
    @DisplayName("4 이상이면 한칸 전진한다.")
    void isStepForwardTest(int number){
        //given
        assertThat(car.isStepForward(number)).isTrue();

        //when
        car.MoveForward();

        //then
        assertThat(car.getStepCount()).isEqualTo(1);
    }



}

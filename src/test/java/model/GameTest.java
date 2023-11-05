package model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {

    Game game;
    List<Car> carList;

    @BeforeEach
    void init(){
        game = new Game();
    }

    @BeforeEach
    void carListInit(){
        carList = new ArrayList<>();

        Car car1 = new Car("붕붕이");
        Car car2 = new Car("아우디");
        Car car3 = new Car("페라리");

        carList.add(car1);
        carList.add(car2);
        carList.add(car3);
    }

    @Test
    @DisplayName("3대의 자동차 중 가장 많이 전진한 자동차의 전진 수는 2")
    void getMaxNumberTest(){
        //given
        setMovingCarList();

        //when
        int maxStepCount = game.getMaxStepCount(carList);

        //then
        assertThat(maxStepCount).isEqualTo(2);
    }

    @Test
    @DisplayName("3대의 자동차 중 최종 우승자는 붕붕이와 아우디")
    void getWinnerListTest(){
        //given
        setMovingCarList();

        //when
        String winnerList = game.getWinnerList(carList, 2);

        //then
        assertThat(winnerList).isEqualTo("최종 우승자 : 붕붕이, 아우디");
    }

    private void setMovingCarList(){
        for(Car car : carList){
            car.MoveForward();
        }
        carList.get(0).MoveForward();
        carList.get(1).MoveForward();
    }

}

package service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import model.Car;
import model.Game;

/**
 * 서로 역할을 가진 Car 와 Game 도메인을 함께 사용하여 기능을 만드는 클래스
 */
public class CarGameService {
    Game game = new Game();

    /**
     * 경주 할 자동차 목록 만들기
     *
     * @param carNames 사용자가 입력한 경주한 자동차 이름들
     * @return 각 자동차 이름을 분리하여 리스트에 담아 리턴한다.
     */
    public List<Car> getCarList(String carNames){
        List<Car> carList = new ArrayList<>();
        String[] carNameArray = carNames.split(",");

        for(String carName : carNameArray){
            carName = carName.trim();
            carList.add(new Car(carName));
        }

        return carList;
    }

    /**
     * 한판의 자동차 경주를 실행하여 각 자동차들은 임의의 값을 기준으로 전진하거나 멈춘다.
     *
     * @param carList 경주에 참여할 자동차
     * @return 경주에 참여한 모든 자동차들의 게임 결과 리턴
     */
    public List<Car> runRacingGame(List<Car> carList){
        Game.tryCount--;
        List<Car> racingGameResult = getRacingGameResult(carList);

        return racingGameResult;
    }

    private List<Car> getRacingGameResult(List<Car> carList){
        for(Car car : carList){
            int randomNumber = car.getRandomNumber();

            if(car.isStepForward(randomNumber)){
                car.MoveForward();
            }
        }

        return carList;
    }

    /**
     * 모든 자동차들의 경주 결과를 "{자동차명} : {전진횟수}" 형태로 가공한다.
     *
     * @param carList 경주한 자동차와 이동횟수를 담은 리스트
     * @return 가공된 전체 경주 결과 리턴
     */
    public List<String> getRacingGameWinnerList(List<Car> carList){
        List<String> racingGameResultList = new ArrayList<>();

        for(Car car : carList){
            String racingGameProgress = getRacingGameProgress(car.getStepCount(), car.getName());
            racingGameResultList.add(racingGameProgress);
        }

        return racingGameResultList;
    }

    private String getRacingGameProgress(int stepCount, String carName){
        String step = "";
        while (stepCount > 0){
            stepCount--;
            step += "-";
        }

        return Stream.of(carName, " : ", step)
                .collect(Collectors.joining());
    }

    /**
     * 자동차 경주 우승자 결과 만들기
     *
     * @param carList 경주한 자동차들의 결과를 담은 List
     * @return 자동차 경주 우승자 결과 리턴
     */
    public String getRacingGameWinner(List<Car> carList){
        int maxStepCount = game.getMaxStepCount(carList);
        String getWinnerList = game.getWinnerList(carList, maxStepCount);

        return getWinnerList;
    }

}

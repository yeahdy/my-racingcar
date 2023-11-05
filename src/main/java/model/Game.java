package model;

import constants.GameConstant;
import constants.MessageType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    public static int tryCount;

    /**
     * 가장 많이 움직인 전진 수
     *
     * @param carList 경주한 자동차들의 결과를 담은 List
     * @return 경주한 자동차 중 가장 많이 움직인 전진 수 리턴
     */
    public int getMaxStepCount(List<Car> carList){
        int[] stepCountArray = carList.stream()
                .mapToInt(Car::getStepCount)
                .toArray();

        return getMaxNumber(stepCountArray);
    }

    private int getMaxNumber(int[] numberArray){
        return Arrays.stream(numberArray)
                .max()
                .orElse(0);
    }

    /**
     * 자동차 중 최대 전진 수와 같은 자동차를 판별하여 우승자를 선발한다.
     *
     * @param carList 경주한 자동차들의 결과를 담은 List
     * @param maxStepCount 경주한 자동차 중 가장 많이 움직인 전진 수
     * @return 우승자를 선발하여 게임메시지데이터를 가공한 후 리턴
     */
    public String getWinnerList(List<Car> carList, int maxStepCount){
        String winnerList = getWinnerResult(carList, maxStepCount);

        winnerList = removeCommaResult(winnerList);

        winnerList = MessageType.GAME_RESULT.getMessage()
                .replace(GameConstant.WINNER_TEXT,winnerList);

        return winnerList;
    }

    private String getWinnerResult(List<Car> carList, int maxStepCount){
        return carList.stream()
                .filter(car -> car.getStepCount() == maxStepCount)
                .map(Car::getName)
                .collect(Collectors.joining(", "));
    }

    private String removeCommaResult(String winnerList){
        if(winnerList.endsWith(", ")){
            winnerList = winnerList.substring(0, winnerList.length() - 2);
        }
        return winnerList;
    }

}


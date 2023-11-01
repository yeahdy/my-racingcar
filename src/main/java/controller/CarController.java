package controller;

import static constants.MessageType.GAME_START;
import static constants.MessageType.PROGRESS_RESULT;
import static constants.MessageType.TRY_COUNT;

import camp.nextstep.edu.missionutils.Console;
import constants.ErrorCodeConstant;
import constants.GameConstant;
import java.util.List;
import model.Car;
import service.CarGameService;
import view.InputView;
import view.OutputView;

public class CarController {
    private OutputView outputView = new OutputView();
    private InputView inputView = new InputView();
    private CarGameService carGameService = new CarGameService();

    public void startRacingGame() {
        //경주할 자동차 이름을 입력
        outputView.printGameMessage(GAME_START);
        String carNames = Console.readLine();
        if(!inputView.isBlank(carNames) || !validateCarNameList(carNames)){
            throw new IllegalArgumentException();
        }

        //경주할 자동차 목록
        outputView.print(carNames);
        List<Car> carList = carGameService.getCarList(carNames);

        //시도할 회수 입력
        outputView.printGameMessage(TRY_COUNT);
        String tryCount = Console.readLine();
        if(!inputView.isBlank(tryCount) || !validateTryCount(tryCount)){
            throw new IllegalArgumentException();
        }

        //실행 결과
        outputView.printGameMessage(PROGRESS_RESULT);
        //자동차 게임 실행()


        //최종 우승자 선정

    }

    /**
     * 사용자가 입력한 자동차 이름 유효성 검사
     * - 공백일 경우 예외 발생
     * - 5자 이하일 경우 예외 발생
     *
     * @param carList
     * @return 예외를 발생 시키지 않으면 true 리턴
     */
    private boolean validateCarNameList(String carList){
        String[] carArray = carList.split(",");
        for(String carName : carArray){
            if(carName.isBlank()){
                throw new IllegalArgumentException(ErrorCodeConstant.STRING_BLANK_ERROR);
            } else if(GameConstant.MAX_NAME_LENGTH < carName.length()){
                throw new IllegalArgumentException(ErrorCodeConstant.NAME_LENGTH_ERROR);
            }
        }
        return true;
    }


    /**
     * 시도 횟수 유효성 검사
     * - 숫자 외의 문자가 있을 경우 예외 발생
     * - int 정수 최대 범위를 초과할 경우 예외 발생
     *
     * @param tryCount
     * @return 예외를 발생 시키지 않으면 true 리턴
     */
    private boolean validateTryCount(String tryCount){
        final String REGEX = "[0-9]+";

        if(!tryCount.matches(REGEX)) {
            throw new IllegalArgumentException(ErrorCodeConstant.NUMBER_VALIDATION_ERROR);
        }

        int tryCountNumber = Integer.parseInt(tryCount);
        if(tryCountNumber > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(ErrorCodeConstant.MAX_NUMBER_ERROR);
        } else if (tryCountNumber == GameConstant.MIN_TRY_COUNT) {
            throw new IllegalArgumentException(ErrorCodeConstant.MIN_TRY_COUNT_ERROR);
        }

        return true;
    }

}
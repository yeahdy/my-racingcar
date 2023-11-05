package controller;

import static constants.MessageType.GAME_START;
import static constants.MessageType.PROGRESS_RESULT;
import static constants.MessageType.TRY_COUNT;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import model.Car;
import model.Game;
import service.CarGameService;
import validators.NameValidator;
import validators.TryCountValidator;
import view.InputView;
import view.OutputView;

public class CarController {
    private OutputView outputView = new OutputView();
    private InputView inputView = new InputView();
    private CarGameService carGameService = new CarGameService();

    public void startRacingGame() {
        //경주할 자동차 이름을 입력
        String carNames = setCarNames();

        //경주할 자동차 목록
        List<Car> carList = carGameService.getCarList(carNames);

        //시도할 회수 입력
        Game.tryCount = setTryCount();

        //자동차 게임 실행
        List<Car> racingGameResult = getRacingGameResult(carList);

        //최종 우승자 선정
        String WinnerList = carGameService.getRacingGameWinner(racingGameResult);
        outputView.print(WinnerList);
        Console.close();
    }

    private String setCarNames(){
        outputView.printGameMessage(GAME_START);
        String carNames = Console.readLine();

        if(!inputView.isNotBlank(carNames) || !NameValidator.verifyNameList(carNames)){
            throw new IllegalArgumentException();
        }
        return carNames;
    }


    private int setTryCount(){
        outputView.printGameMessage(TRY_COUNT);
        String tryCount = Console.readLine();

        if(!inputView.isNotBlank(tryCount) || !TryCountValidator.verifyTryCount(tryCount)){
            throw new IllegalArgumentException();
        }
        return Integer.parseInt(tryCount);
    }

    private List<Car> getRacingGameResult(List<Car> carList){
        outputView.printGameMessage(PROGRESS_RESULT);

        List<Car> racingGameResult = null;
        while (Game.tryCount > 0){
            racingGameResult = carGameService.runRacingGame(carList);
            printWinnerList(racingGameResult);
        }
        return racingGameResult;
    }

    private void printWinnerList(List<Car> racingGameResult){
        List<String> getRacingGameWinnerList = carGameService.getRacingGameWinnerList(racingGameResult);
        for(String result : getRacingGameWinnerList){
            outputView.print(result);
        }
        outputView.print("");
    }

}

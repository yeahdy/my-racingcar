package validators;

import constants.ErrorCodeConstant;
import constants.GameConstant;

public class NameValidator {

    /**
     * 사용자가 입력한 자동차 이름 유효성 검사
     * - 공백일 경우 예외 발생
     * - 5자 이하일 경우 예외 발생
     *
     * @param carList
     * @return 예외를 발생 시키지 않으면 true 리턴
     */
    public static boolean verifyNameList(String carList){
        String[] carArray = carList.split(",");

        for(String carName : carArray){
            isMaxNameLength(carName.length());
        }
        return true;
    }

    private static void isMaxNameLength(int carNameLength){
        if(GameConstant.MAX_NAME_LENGTH < carNameLength){
            throw new IllegalArgumentException(ErrorCodeConstant.NAME_LENGTH_ERROR);
        }
    }

}

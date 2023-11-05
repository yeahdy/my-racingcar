package validators;

import constants.ErrorCodeConstant;
import constants.GameConstant;

public class TryCountValidator {

    /**
     * 시도 횟수 유효성 검사
     * - 숫자 외의 문자가 있을 경우 예외 발생
     * - int 정수 최대 범위를 초과할 경우 예외 발생
     * - 숫자 0 입력할 경우 예외 발생
     *
     * @param tryCount
     * @return 예외를 발생 시키지 않으면 true 리턴
     */
    public static boolean verifyTryCount(String tryCount){
        isOnlyNumber(tryCount);

        int tryCountNumber = Integer.parseInt(tryCount);
        isNotMaxNumber(tryCountNumber);
        isMinNumber(tryCountNumber);

        return true;
    }

    private static void isOnlyNumber(String tryCount){
        final String REGEX = "[0-9]+";

        if(!tryCount.matches(REGEX)) {
            throw new IllegalArgumentException(ErrorCodeConstant.NUMBER_VALIDATION_ERROR);
        }
    }

    private static void isNotMaxNumber(int tryCount){
        if(tryCount > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(ErrorCodeConstant.MAX_NUMBER_ERROR);
        }
    }

    private static void isMinNumber(int tryCount){
        if (tryCount == GameConstant.MIN_TRY_COUNT) {
            throw new IllegalArgumentException(ErrorCodeConstant.MIN_TRY_COUNT_ERROR);
        }
    }

}

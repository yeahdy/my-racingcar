package view;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class InputViewTest {

    InputView inputView;

    @BeforeEach
    void init(){
        inputView = new InputView();
    }

    @DisplayName("공백을 넣으면 예외 발생")
    @ParameterizedTest
    @ValueSource(strings = {""," "})
    void isBlankTest(String data){
        Assertions.assertThatThrownBy(() -> assertThat(inputView.isNotBlank(data)).isTrue())
                .isInstanceOf(IllegalArgumentException.class);
    }

}

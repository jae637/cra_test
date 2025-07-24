package mission2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarTest {

    Car car = new Car();
    Car spyCar = spy(car);

    @Test
    void executeStep_정상_input() {
        // given
        spyCar.executeStep(1);
        spyCar.executeStep(1);
        spyCar.executeStep(1);
        spyCar.executeStep(1);

        // when
        spyCar.executeStep(2);

        // then
        verify(spyCar,times(0)).fail(anyString());
    }
//
//    @Test
//    void executeStep_정상2_input() {
//        // given
//        spyCar.executeStep(2);
//        spyCar.executeStep(2);
//        spyCar.executeStep(2);
//        spyCar.executeStep(2);
//
//        // when
//        spyCar.executeStep(2);
//
//        // then
//        verify(spyCar,times(0)).fail(anyString());
//    }

    @Test
    void executeStep_이전단계_요청_input() {
        // given
        car.executeStep(2);

        // when
        car.executeStep(0);

        // then
        assertEquals(0,car.getStep());
    }

    @Test
    void executeStep_처음부터_다시하기_input() {
        //given
        car.executeStep(2);
        car.executeStep(2);
        car.executeStep(2);
        car.executeStep(2);

        // when
        car.executeStep(0);

        //then
        assertEquals(0,car.getStep());
    }

    @Test
    void executeStep_잘못만든_자동차_input() {
        //given
        spyCar.executeStep(2);
        spyCar.executeStep(2);
        spyCar.executeStep(2);
        spyCar.executeStep(2);

        // when
        spyCar.executeStep(2);

        //then
        verify(spyCar).fail(anyString());
    }

    @Test
    void executeStep_고장난_엔진의_자동차_input() {
        spyCar.executeStep(1);
        spyCar.executeStep(4);
        spyCar.executeStep(1);
        spyCar.executeStep(1);

        spyCar.executeStep(1);
        spyCar.executeStep(2);

        verify(spyCar,times(0)).fail(anyString());
    }

    @Test
    void executeStep_잘못만든_자동차_input2() {
        spyCar.executeStep(2);
        spyCar.executeStep(2);
        spyCar.executeStep(2);
        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
    }


    @Test
    void executeStep_범위밖_input_1() {

        car.executeStep(0);

        assertEquals(0,car.getStep());
    }

    @Test
    void executeStep_범위밖_input_2() {
        car.executeStep(2);
        car.executeStep(2);
        car.executeStep(2);
        car.executeStep(2);

        assertFalse(car.isValidRange(4));
    }

    @Test
    void executeStep_logic_테스트_1() {
        spyCar.executeStep(1);
        spyCar.executeStep(1);
        spyCar.executeStep(3);
        spyCar.executeStep(2);

        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
        verify(spyCar).fail(anyString());
    }

    @Test
    void executeStep_logic_테스트_2() {
        spyCar.executeStep(1);
        spyCar.executeStep(1);
        spyCar.executeStep(2);
        spyCar.executeStep(1);

        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
        verify(spyCar).fail(anyString());
    }
    @Test
    void executeStep_logic_테스트_3() {
        spyCar.executeStep(2);
        spyCar.executeStep(2);
        spyCar.executeStep(1);
        spyCar.executeStep(1);

        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
        verify(spyCar).fail(anyString());
    }
    @Test
    void executeStep_logic_테스트_4() {
        spyCar.executeStep(3);
        spyCar.executeStep(3);
        spyCar.executeStep(1);
        spyCar.executeStep(1);

        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
        verify(spyCar).fail(anyString());
    }
    @Test
    void executeStep_logic_테스트_5() {
        spyCar.executeStep(3);
        spyCar.executeStep(1);
        spyCar.executeStep(1);
        spyCar.executeStep(1);

        spyCar.executeStep(1);
        spyCar.executeStep(2);

        assertFalse(spyCar.isValidCheck());
        verify(spyCar).fail(anyString());
    }

}
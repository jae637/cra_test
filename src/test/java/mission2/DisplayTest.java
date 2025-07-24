package mission2;

import mission2.car.Car;
import mission2.display.Display;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class DisplayTest {

    Display display = new Display();
    Display spyDisplay = spy(Display.class);

    @Test
    void displayStep() {
        Car mockCar = mock(Car.class);
        when(mockCar.getStep())
                .thenReturn(0)
                .thenReturn(1)
                .thenReturn(2)
                .thenReturn(3)
                .thenReturn(4);

        spyDisplay.displayStep(mockCar);
        spyDisplay.displayStep(mockCar);
        spyDisplay.displayStep(mockCar);
        spyDisplay.displayStep(mockCar);
        spyDisplay.displayStep(mockCar);
    }
}
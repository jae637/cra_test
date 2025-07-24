package mission2;

import java.util.Objects;

public class CarType implements CarTypeInterface{
    public int getCarType() {
        return carType;
    }

    private int carType;

    @Override
    public void selectCarType(int a) {
        this.carType = a;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", a == 1 ? "Sedan" : a == 2 ? "SUV" : "Truck");
    }
}

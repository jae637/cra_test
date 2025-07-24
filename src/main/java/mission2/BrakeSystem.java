package mission2;

public class BrakeSystem implements BrakeSystemInterface{
    private int brakeSystem;

    public int getBrakeSystem() {
        return brakeSystem;
    }

    @Override
    public void selectBrakeSystem(int a) {
        brakeSystem = a;
        String name = a == 1 ? "MANDO" : a == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
}

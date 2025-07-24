package mission2;

public class SteeringSystem implements SteeringSystemInterface{
    private int steeringSystem;

    public int getSteeringSystem() {
        return steeringSystem;
    }

    @Override
    public void selectOptions(int a) {
        this.steeringSystem = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }
}

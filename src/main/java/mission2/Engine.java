package mission2;

public class Engine implements EngineInterface{
    private int engine;

    public int getEngine() {
        return engine;
    }

    @Override
    public void selectEngine(int a) {
        this.engine = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
}

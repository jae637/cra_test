package mission2;

import static mission2.BrakeSystemInterface.*;
import static mission2.CarTypeInterface.*;
import static mission2.EngineInterface.TOYOTA;
import static mission2.EngineInterface.WIA;
import static mission2.SteeringSystemInterface.BOSCH_S;

public class Car {
    public static final int CarType_Q      = 0;
    public static final int Engine_Q       = 1;
    public static final int BrakeSystem_Q  = 2;
    public static final int SteeringSystem_Q = 3;
    public static final int Run_Test       = 4;


    private int step;
    private CarType carType = new CarType();
    private Engine engine = new Engine();
    private BrakeSystem brakeSystem = new BrakeSystem();
    private SteeringSystem steeringSystem = new SteeringSystem();

    public boolean isValidCheck() {
        if ( carType.getCarType() == SEDAN && brakeSystem.getBrakeSystem() == CONTINENTAL) return false;
        if ( carType.getCarType() == SUV   && engine.getEngine() == TOYOTA)       return false;
        if ( carType.getCarType() == TRUCK && engine.getEngine() == WIA)          return false;
        if ( carType.getCarType() == TRUCK && brakeSystem.getBrakeSystem() == MANDO)  return false;
        if (brakeSystem.getBrakeSystem() == BOSCH_B && steeringSystem.getSteeringSystem() != BOSCH_S) return false;
        return true;
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    public void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    public void testProducedCar() {
        if (carType.getCarType() == SEDAN && brakeSystem.getBrakeSystem() == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (carType.getCarType() ==  SUV && engine.getEngine() ==  TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carType.getCarType() ==  TRUCK && engine.getEngine() ==  WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (carType.getCarType() ==  TRUCK && brakeSystem.getBrakeSystem() ==  MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (brakeSystem.getBrakeSystem() ==  BOSCH_B && steeringSystem.getSteeringSystem() !=  BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public void runProducedCar() {
        if (! isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (engine.getEngine() == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[carType.getCarType()]);
        System.out.printf("Engine   : %s\n", engNames[engine.getEngine()]);
        System.out.printf("Brake    : %s\n",
                brakeSystem.getBrakeSystem()==1? "Mando":
                        brakeSystem.getBrakeSystem()==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                steeringSystem.getSteeringSystem()==1? "Bosch":"Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    public int getStep() {
        return step;
    }

    public void excuteStep(Integer answer) {
        if (answer == 0) {
            if (this.step == Run_Test) {
                this.step = CarType_Q;
            } else if (this.step > CarType_Q) {
                this.step--;
            }
        }

        switch (this.step) {
            case CarType_Q:
                carType.selectCarType(answer);
                delay(800);
                this.step = Engine_Q;
                break;
            case Engine_Q:
                engine.selectEngine(answer);
                delay(800);
                this.step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                brakeSystem.selectBrakeSystem(answer);
                delay(800);
                this.step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                steeringSystem.selectSteeringSystem(answer);
                delay(800);
                this.step = Run_Test;
                break;
            case Run_Test:
                if (answer == 1) {
                    runProducedCar();
                    delay(2000);
                } else if (answer == 2) {
                    System.out.println("Test...");
                    delay(1500);
                    testProducedCar();
                    delay(2000);
                }
                break;
        }
    }
}

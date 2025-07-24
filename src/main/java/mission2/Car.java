package mission2;

public class Car {
    public static final int CarType_Q      = 0;
    public static final int Engine_Q       = 1;
    public static final int BrakeSystem_Q  = 2;
    public static final int SteeringSystem_Q = 3;
    public static final int Run_Test       = 4;

    public static final int SEDAN = 1, SUV = 2, TRUCK = 3;
    public static final int GM = 1, TOYOTA = 2, WIA = 3;
    public static final int MANDO = 1, CONTINENTAL = 2, BOSCH_B = 3;
    public static final int BOSCH_S = 1, MOBIS = 2;
    
    private int step;
    private int carType;
    private int engine;
    private int brakeSystem;
    private int steeringSystem;

    public boolean isValidCheck() {
        if ( carType == SEDAN && brakeSystem == CONTINENTAL) return false;
        if ( carType == SUV   && engine == TOYOTA)       return false;
        if ( carType == TRUCK && engine == WIA)          return false;
        if ( carType == TRUCK && brakeSystem == MANDO)  return false;
        if (brakeSystem == BOSCH_B && steeringSystem != BOSCH_S) return false;
        return true;
    }

    public void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {}
    }

    public   void fail(String msg) {
        System.out.println("자동차 부품 조합 테스트 결과 : FAIL");
        System.out.println(msg);
    }

    public void testProducedCar() {
        if (carType == SEDAN && brakeSystem == CONTINENTAL) {
            fail("Sedan에는 Continental제동장치 사용 불가");
        } else if (carType ==  SUV && engine ==  TOYOTA) {
            fail("SUV에는 TOYOTA엔진 사용 불가");
        } else if (carType ==  TRUCK && engine ==  WIA) {
            fail("Truck에는 WIA엔진 사용 불가");
        } else if (carType ==  TRUCK && brakeSystem ==  MANDO) {
            fail("Truck에는 Mando제동장치 사용 불가");
        } else if (brakeSystem ==  BOSCH_B && steeringSystem !=  BOSCH_S) {
            fail("Bosch제동장치에는 Bosch조향장치 이외 사용 불가");
        } else {
            System.out.println("자동차 부품 조합 테스트 결과 : PASS");
        }
    }

    public   void runProducedCar() {
        if (! isValidCheck()) {
            System.out.println("자동차가 동작되지 않습니다");
            return;
        }
        if (engine == 4) {
            System.out.println("엔진이 고장나있습니다.");
            System.out.println("자동차가 움직이지 않습니다.");
            return;
        }

        String[] carNames = {"", "Sedan", "SUV", "Truck"};
        String[] engNames = {"", "GM", "TOYOTA", "WIA"};
        System.out.printf("Car Type : %s\n", carNames[carType]);
        System.out.printf("Engine   : %s\n", engNames[engine]);
        System.out.printf("Brake    : %s\n",
                brakeSystem==1? "Mando":
                        brakeSystem==2? "Continental":"Bosch");
        System.out.printf("Steering : %s\n",
                steeringSystem==1? "Bosch":"Mobis");
        System.out.println("자동차가 동작됩니다.");
    }

    public void setStep(int step) {
        this.step = step;
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
                selectCarType(answer);
                delay(800);
                this.step = Engine_Q;
                break;
            case Engine_Q:
                selectEngine(answer);
                delay(800);
                this.step = BrakeSystem_Q;
                break;
            case BrakeSystem_Q:
                selectBrakeSystem(answer);
                delay(800);
                this.step = SteeringSystem_Q;
                break;
            case SteeringSystem_Q:
                selectSteeringSystem(answer);
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


    private void selectCarType(int a) {
        this.carType = a;
        System.out.printf("차량 타입으로 %s을 선택하셨습니다.\n", a == 1 ? "Sedan" : a == 2 ? "SUV" : "Truck");
    }
    private void selectEngine(int a) {
        this.engine = a;
        String name = a == 1 ? "GM" : a == 2 ? "TOYOTA" : a == 3 ? "WIA" : "고장난 엔진";
        System.out.printf("%s 엔진을 선택하셨습니다.\n", name);
    }
    private void selectBrakeSystem(int a) {
        this.brakeSystem = a;
        String name = a == 1 ? "MANDO" : a == 2 ? "CONTINENTAL" : "BOSCH";
        System.out.printf("%s 제동장치를 선택하셨습니다.\n", name);
    }
    private void selectSteeringSystem(int a) {
        this.steeringSystem = a;
        String name = a == 1 ? "BOSCH" : "MOBIS";
        System.out.printf("%s 조향장치를 선택하셨습니다.\n", name);
    }
}

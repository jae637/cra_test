package mission2;

import java.util.Scanner;

public class Assemble {

    private static int[] stack = new int[5];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Car car = new Car();
        Display display = new Display();

        while (true) {
            display.displayStep(car);

            //exit 입력 시 null
            String buf = selectOption(sc);
            if (buf == null) break;

            // input 유효성 검사 실패 시 null
            Integer answer = validAndParseValue(buf, car);
            if (answer == null) continue;

            car.executeStep(answer);
        }

        sc.close();
    }

    private static String selectOption(Scanner sc) {
        String buf = sc.nextLine().trim();

        if (buf.equalsIgnoreCase("exit")) {
            System.out.println("바이바이");
            return null;
        }
        return buf;
    }

    private static Integer validAndParseValue(String buf, Car car) {
        int answer;
        try {
            answer = Integer.parseInt(buf);
        } catch (NumberFormatException e) {
            System.out.println("ERROR :: 숫자만 입력 가능");
            car.delay(800);
            return null;
        }

        if (!isValidRange(car.getStep(), answer)) {
            car.delay(800);
            return null;
        }
        return answer;
    }

    private static boolean isValidRange(int step, int ans) {
        switch (step) {
            case Car.CarType_Q:
                if (ans < 1 || ans > 3) {
                    System.out.println("ERROR :: 차량 타입은 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Car.Engine_Q:
                if (ans < 0 || ans > 4) {
                    System.out.println("ERROR :: 엔진은 1 ~ 4 범위만 선택 가능");
                    return false;
                }
                break;
            case Car.BrakeSystem_Q:
                if (ans < 0 || ans > 3) {
                    System.out.println("ERROR :: 제동장치는 1 ~ 3 범위만 선택 가능");
                    return false;
                }
                break;
            case Car.SteeringSystem_Q:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: 조향장치는 1 ~ 2 범위만 선택 가능");
                    return false;
                }
                break;
            case Car.Run_Test:
                if (ans < 0 || ans > 2) {
                    System.out.println("ERROR :: Run 또는 Test 중 하나를 선택 필요");
                    return false;
                }
                break;
        }
        return true;
    }


}
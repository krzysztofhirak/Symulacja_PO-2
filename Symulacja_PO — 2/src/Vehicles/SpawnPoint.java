package Vehicles;

import java.awt.*;
import java.util.ArrayList;

public class SpawnPoint {

    public SpawnPoint() {
    }

    public static void createVehicle(ArrayList<Car> car, ArrayList<Motor> motor, int car_amount, int motor_amount){
        for(int i=0;i<car_amount;i++){//to tutaj do przerobienia ale dopiero jak bedziemy wiedziec gdzie start
            double[] start_point = {Math.random()*400,Math.random()*400};
            car.add(new Car(start_point,(Math.random()*3+1),(int)(Math.random()*3)*90));
        }
        for(int i=0;i<motor_amount;i++){//to tutaj do przerobienia ale dopiero jak bedziemy wiedziec gdzie start
            double[] start_point = {Math.random()*400,Math.random()*400};
            motor.add(new Motor(start_point,(Math.random()*3+3),(int)(Math.random()*3)*90));
        }
    }

    public static void spawnVehicle(Graphics window, ArrayList<Car> car, ArrayList<Motor> motor, int FRAME_WIDTH, int FRAME_HEIGHT, int CAR_AMOUNT, int MOTOR_AMOUNT) {
        for (Car car_model: car) {
            car_model.paintView(window);
            car_model.paintVehicle(window);
            car_model.drive(car,motor,CAR_AMOUNT,MOTOR_AMOUNT);
            car_model.drive(FRAME_WIDTH,FRAME_HEIGHT);
        }

        for (Motor motor_model: motor) {
            motor_model.paintView(window);
            motor_model.paintVehicle(window);
            motor_model.drive(car,motor,CAR_AMOUNT,MOTOR_AMOUNT);
            motor_model.drive(FRAME_WIDTH,FRAME_HEIGHT);
        }

    }

}

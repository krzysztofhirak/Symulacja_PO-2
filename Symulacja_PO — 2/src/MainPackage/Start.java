package MainPackage;

import Environment.Buttons;
import Vehicles.Car;
import Vehicles.Motor;
import Vehicles.SpawnPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Start extends JPanel implements ActionListener {

    private final int FRAME_HEIGHT = 800;
    private final int FRAME_WIDTH = 1200;
    private final int CAR_AMOUNT = 20;
    private final int MOTOR_AMOUNT = 10;
    public double max_speed = 0;
    double time = 0;

    int i = 0;

    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Motor> motors = new ArrayList<>();

    Buttons buttons = new Buttons();

    JFrame frame = new JFrame("Symulacja");
    JTextField car_max_speed = new JTextField();
    JTextField motor_max_speed = new JTextField();
    JButton button = new JButton("Apply");

    String CarSpeedInput = "";
    String MotorSpeedInput = "";

//    Timer timer1 = new Timer(17, new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
//            repaint();
//        }
//    });

    public Start() {

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.cyan);
        frame.setResizable(false);

        SpawnPoint.createVehicle(cars,motors,CAR_AMOUNT,MOTOR_AMOUNT);

        Timer timer = new Timer(17,this);
        timer.restart();

        frame.add(this);
        frame.setVisible(true);
    }

    public void paint(Graphics window) {

        paintButtons(window);
        paintSimulation(window);

        time++;

//        buttons.paint(window);
    }

    public void paintSimulation(Graphics g){
        super.paintComponent(g);

        SpawnPoint.spawnVehicle(g, cars, motors, FRAME_WIDTH, FRAME_HEIGHT, CAR_AMOUNT, MOTOR_AMOUNT);

        g.setColor(Color.black);
        g.drawLine(FRAME_WIDTH - FRAME_WIDTH / 3, 0, FRAME_WIDTH - FRAME_WIDTH / 3, FRAME_HEIGHT);
        g.setColor(new Color(200, 207, 218));
        g.fillRect(FRAME_WIDTH - FRAME_WIDTH / 3, 0, FRAME_WIDTH / 3, FRAME_HEIGHT);
        g.setColor(Color.black);
        g.drawString("Cars: " + "   Time: " + time, FRAME_WIDTH - FRAME_WIDTH / 3 + 30, 30);
//        window.drawString("Collisions: ", FRAME_WIDTH - FRAME_WIDTH / 3 + 30, 45);
        g.drawString(String.format("Car max speed: %.2f",Math.abs(max_speed)), 950, 70);
    }

    public void paintButtons(Graphics g){
        super.paint(g);

        this.add(car_max_speed);
        car_max_speed.setBounds(820, 70, 50, 25);
        car_max_speed.addActionListener(this);
        car_max_speed.setVisible(true);

        motor_max_speed.setBounds(820, 100, 50, 25);
        this.add(motor_max_speed);
        motor_max_speed.addActionListener(this);
        motor_max_speed.setVisible(true);

        button.setBounds(820,130,90,25);
        this.add(button);
        button.addActionListener(this);
        button.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
//        time++;

        if(e.getSource()==button){
            cars.get(i).turnLeft();
            if(i<19){
                i++;
            }
            else{
                i=0;
            }
        }

        if(e.getSource()== car_max_speed){
            CarSpeedInput = car_max_speed.getText();
            int SpeedInputNumber=Integer.parseInt(CarSpeedInput);
            for(int i = 0; i < CAR_AMOUNT; i++){
                cars.get(i).setMaxSpeed(SpeedInputNumber);
            }
            max_speed = SpeedInputNumber;
        }
        if(e.getSource()== motor_max_speed){
            MotorSpeedInput = motor_max_speed.getText();
            int SpeedInputNumber=Integer.parseInt(MotorSpeedInput);
            for(int i = 0; i < MOTOR_AMOUNT; i++){
                motors.get(i).setMaxSpeed(SpeedInputNumber);
            }
        }
    }

}
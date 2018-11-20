package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//test
@TeleOp(name="Teleop : Swippo", group="Swippo")
public class SwippoTeleop extends OpMode{

    public static final String VUFORIA_KEY = "AY98fJD/////AAABmbqEQnRPkUDiuJvPxoEJVTxg7ZIIGZ/WhiglMsbq6v0777eQIRgD5PiaCJ9i5Uvo0RY7Q6WMrVJqs3e8AwMD4QzSyhyNyaGZfYqMdZZYQbzai8TGiWLzTsMLuLEMl6fDKK79m6+vV8rX0UPrPrX3EIXRW8sihCLSVvLjU5Y2xYglDIdSA2aiN4FB1YnDq/Aggc5VqKD56BydSDRpDEf88E/Q9X46u7VuLx+gm70ZnGHmRUQX70Ph7LkR3HM43Ar6bAEvkTTdJtWy1MwaSPCfFc31UX22kBxvHxs5xStEvNONSDcDknsnwzINNAXLyrM9C5jC3OJqJjfJKha4VaqFNuQPTDRzemEQ4j6FXTKRE7Ax";

    HardwareBuggo robot = new HardwareBuggo();
    boolean closed = false;
    int close_count = 0;
    double speed_multiplier = 1;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        robot.init(hardwareMap);
        initVuforia();
        telemetry.addData("Say", "Hello Driver");
        updateTelemetry(telemetry);
    }

    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    @Override
    public void loop() {

        double threshold = 0.1;
        updateTelemetry(telemetry);



        //debug
//        telemetry.addData("count ", close_count);
//        telemetry.update();
//
//
//        if(gamepad2.left_stick_button || gamepad2.right_stick_button){
//            speed_multiplier = 1;
//        }else{
//            speed_multiplier = 0.5;
//        }
//
//        if(gamepad2.left_stick_y == 0 && gamepad2.left_stick_x == 0 && !(Math.abs(gamepad2.right_stick_x) > threshold))
//        {
//            robot.fr.setPower(0);
//            robot.fl.setPower(0);
//            robot.br.setPower(0);
//            robot.bl.setPower(0);
//        }
//        else// if(Math.abs(gamepad2.left_stick_y) > threshold || Math.abs(gamepad2.left_stick_x) > threshold)
//        {
//            robot.fl.setPower(speed_multiplier *( (gamepad2.left_stick_y - gamepad2.left_stick_x)/2-(gamepad2.right_stick_x)/2));
//            robot.bl.setPower(speed_multiplier *( (gamepad2.left_stick_y + gamepad2.left_stick_x)/2-(gamepad2.right_stick_x)/2));
//            robot.fr.setPower(speed_multiplier *( (-gamepad2.left_stick_y - gamepad2.left_stick_x)/2-(gamepad2.right_stick_x)/2));
//            robot.br.setPower(speed_multiplier *( (-gamepad2.left_stick_y + gamepad2.left_stick_x)/2-(gamepad2.right_stick_x)/2));
//        }

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        telemetry.addData("stopping", 0);
        telemetry.update();
    }

}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

//test
@TeleOp(name="Teleop : Buggo", group="Buggo")
public class BuggoTeleop extends OpMode{


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

        telemetry.addData("gamepad1 LX", gamepad1.left_stick_x);
        telemetry.addData("gamepad1 LY", gamepad1.left_stick_y);
        telemetry.addData("gamepad1 RX", gamepad1.right_stick_x);
        telemetry.addData("gamepad1 RY", gamepad1.right_stick_y);
        updateTelemetry(telemetry);

        if(gamepad1.a){
            robot.turnServoContinuous(1);
        }else if(gamepad1.b){
            robot.turnServoContinuous(-1);
        }else{
            robot.stopServoContinuous();
        }

        if(gamepad1.x) {
            robot.driveLimitlessS(1);
        } else if(gamepad1.y) {
            robot.driveLimitlessS(-1);
        } else {
            robot.driveLimitlessS(0);
        }

        if(gamepad1.dpad_down) {
            robot.spinFloat();
        } else if (gamepad1.dpad_up) {
            robot.spinBrake();
        }

        if(gamepad1.dpad_left) {
            robot.rotateArm(1);
        } else if (gamepad1.dpad_right) {
            robot.rotateArm(-1);
        } else {
            robot.rotateArm(0);
        }

        if(gamepad2.dpad_left) {
            robot.moveCascade(1);
        } else if (gamepad2.dpad_right) {
            robot.moveCascade(-1);
        } else {
            robot.moveCascade(0);
        }
        double leftDrivePower = gamepad1.left_stick_y;
        double rightDrivePower = gamepad1.right_stick_y;
        if(Math.abs(leftDrivePower) < 0.05) leftDrivePower = 0;
        if(Math.abs(rightDrivePower) < 0.05) rightDrivePower = 0;
        robot.drive(leftDrivePower, rightDrivePower);

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

package org.firstinspires.ftc.teamcode.test.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDCoefficients;
import net.frogbots.ftcopmodetunercommon.opmode.TunableOpMode;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.ExpansionHubServo;

import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.GLOBALS.*;


@TeleOp(name = "tunes a lot of stuff", group = "")
public class SlidePIDTuner extends TunableOpMode {
    private ExpansionHubMotor leftSlide;
    private ExpansionHubMotor rightSlide;
    private ExpansionHubServo flipper,gripper,rotater;
    private ArrayList<ExpansionHubMotor> allMotors = new ArrayList<>();
    
;
    private double newSlidePosition;


    
    @Override
    public void init() {

        gripper = hardwareMap.get(ExpansionHubServo.class, "gripper");
        flipper = hardwareMap.get(ExpansionHubServo.class, "flipper");
        rotater = hardwareMap.get(ExpansionHubServo.class, "rotater");
        leftSlide = hardwareMap.get(ExpansionHubMotor.class, "leftSlide");
        rightSlide = hardwareMap.get(ExpansionHubMotor.class, "rightSlide");
        
        allMotors.add(leftSlide);
        allMotors.add(rightSlide);
        
        for(ExpansionHubMotor dcMotorEx : allMotors) {
            dcMotorEx.setTargetPosition(0);
            dcMotorEx.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            dcMotorEx.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            dcMotorEx.setPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDCoefficients(P,I,D));
        }

        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        telemetry.addLine("starting PID at: " +P+", " + I + ", " + D);
        telemetry.update();



    }
    
    @Override
    public void start() {
        flipReady();
        rotaterReady();
        gripReady();
    }
    
    @Override
    public void loop() {

        rotaterOut = getDouble("rotaterOut");
        rotaterHome = getDouble("rotaterHome");
        P = getDouble("P");
        I = getDouble("I");
        D = getDouble("D");


        // flipper arm control

        if(gamepad2.dpad_down) {
            flipper.setPosition(flipperBetween);
        }

        if(gamepad2.dpad_up) {
            flipper.setPosition(flipperBetween);
        }

        if(gamepad2.right_trigger > 0.5) {
            flipper.setPosition(flipperHome);
        }

        if(gamepad2.left_trigger > 0.5) {
            flipper.setPosition(flipperOut);
        }



        // rotater arm control
        if(gamepad2.left_bumper) {
            rotaterOut();
        }
        if(gamepad2.right_bumper) {
            rotaterReady();
        }


        // gripper arm control
        if(gamepad2.a) {
            grip();
        }
        if(gamepad2.y) {
            gripReady();
        }
        
        
        
        
        
        
        leftSlide.setPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDCoefficients(P,I,D));
        rightSlide.setPIDCoefficients(DcMotor.RunMode.RUN_TO_POSITION, new PIDCoefficients(P,I,D));

        double increment = gamepad2.right_stick_y * 100;
        if(Math.abs(increment) > 25) {
            newSlidePosition = leftSlide.getCurrentPosition() + increment;
        }
        if(gamepad2.x) {
            newSlidePosition = -25;
        }

        if(gamepad2.b) {
            newSlidePosition =  -50;
        }
        if(gamepad2.dpad_up) {
            newSlidePosition = -300;
        }





        if(Math.abs(newSlidePosition - leftSlide.getCurrentPosition()) > 10 || Math.abs(newSlidePosition - rightSlide.getCurrentPosition()) > 10) {
            leftSlide.setTargetPosition((int)(newSlidePosition));
            rightSlide.setTargetPosition((int)(newSlidePosition));
            leftSlide.setPower(1);
            rightSlide.setPower(1);
        }

        else {
            leftSlide.setPower(0);
            rightSlide.setPower(0);
        }




        telemetry.addData("left slide position", leftSlide.getCurrentPosition());
        telemetry.addData("right slide position", rightSlide.getCurrentPosition());
        telemetry.addData("target position", newSlidePosition);
        telemetry.addData("flipepr out", flipperOut);
        telemetry.addData("flipper intermediate", flipperBetween);
        telemetry.addData("P", P);
        telemetry.addData("I", I);
        telemetry.addData("D", D);

    }


    public void flip() {
        flipper.setPosition(flipperOut);
    }

    public void flipReady() {
        flipper.setPosition(flipperHome);
    }

    public void flipMid() {
        flipper.setPosition(flipperBetween);}


    /**
     * gripper controls
     */
    public void grip() {
        gripper.setPosition(gripperGrip);
    }

    public void gripReady() {
        gripper.setPosition(gripperHome);
    }



    /**
     * rotater movement controls
     */

    public void rotaterOut() {
        rotater.setPosition(rotaterOut);
    }

    public void rotaterReady() {
        rotater.setPosition(rotaterHome);
    }

}

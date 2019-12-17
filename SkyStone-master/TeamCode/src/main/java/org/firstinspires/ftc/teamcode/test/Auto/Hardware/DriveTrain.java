package org.firstinspires.ftc.teamcode.test.Auto.Hardware;

import android.annotation.SuppressLint;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.test.Auto.HelperClasses.Firefly;
import org.openftc.revextensions2.ExpansionHubMotor;

import static org.firstinspires.ftc.teamcode.test.Auto.HelperClasses.Firefly.*;
import static org.firstinspires.ftc.teamcode.GLOBALS.*;
import java.util.ArrayList;

public class DriveTrain {
    // links
    private Firefly robot;
    private ExpansionHubMotor frontLeft, backLeft, frontRight, backRight;
    private boolean isDebugging;


    public DriveTrain(Firefly robot, ExpansionHubMotor frontLeft, ExpansionHubMotor backLeft, ExpansionHubMotor frontRight, ExpansionHubMotor backRight) {
        ArrayList<ExpansionHubMotor> allMotors = new ArrayList<>();

        this.robot = robot;
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.backLeft = backLeft;
        this.backRight = backRight;

        allMotors.add(frontLeft);
        allMotors.add(frontRight);
        allMotors.add(backLeft);
        allMotors.add(backRight);


        for(ExpansionHubMotor motor : allMotors) {
            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }

    }



    private void ApplyMovement() {
        double[] rawPowers = {
                movementY - movementTurn + movementX * 1.5,  // fl
                movementY - movementTurn - movementX * 1.5, // bl
                -movementY - movementTurn + movementX * 1.5, // fr
                -movementY - movementTurn - movementX * 1.5 // br
        };



        double maxRawPower = frontLeft.getPower();
        for(int i=1; i<4; i++) {
            if(rawPowers[i] > maxRawPower) {
                maxRawPower = rawPowers[i];
            }
        }

        double scaleAmt = 1.0;
        if(maxRawPower > 1.0) {
            scaleAmt = 1.0 / maxRawPower;
        }


        frontLeft.setPower(rawPowers[0] * scaleAmt);
        backLeft.setPower(rawPowers[1] * scaleAmt);
        frontRight.setPower(rawPowers[2] * scaleAmt);
        backRight.setPower(rawPowers[3] * scaleAmt);


    }


    @SuppressLint("DefaultLocale")
    private String mecanumPowers() {
        return String.format(
                "\n" +
                        "(%.1f)---(%.1f)\n" +
                        "|   Front   |\n" +
                        "|             |\n" +
                        "|             |\n" +
                        "(%.1f)---(%.1f)\n"
                , frontLeft.getPower(), frontRight.getPower(), backLeft.getPower(), backRight.getPower());
    }


    private void ControlDebugging() {
        robot.telemetry.addLine( header + " DRIVE TRAIN TELEM " + header);
        robot.telemetry.addLine("movement Y: " + movementY);
        robot.telemetry.addLine("movement X: " + movementX);
        robot.telemetry.addLine("movement turn: " + movementTurn);
        robot.telemetry.addLine(mecanumPowers());
    }




    public void update() {
        ApplyMovement();
        ControlDebugging();
    }


}

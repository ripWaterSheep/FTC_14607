package org.firstinspires.ftc.teamcode.test.Auto.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.teamcode.test.Auto.HelperClasses.Firefly;
import org.openftc.revextensions2.ExpansionHubMotor;

import java.util.ArrayList;

public class Intake {
    // link to base class
    private ExpansionHubMotor leftIntake, rightIntake;
    private Firefly robot;
    private ArrayList<ExpansionHubMotor> allMotors = new ArrayList<>();


    // intake specific stuff
    private  enum intakeStates {
        ON,
        OFF,
        REVERSE
    }

    public Intake(Firefly robot, ExpansionHubMotor leftIntake, ExpansionHubMotor rightIntake) {
        this.robot = robot;
        this.leftIntake = leftIntake;
        this.rightIntake = rightIntake;


        allMotors.add(leftIntake);
        allMotors.add(rightIntake);

        for(ExpansionHubMotor intakeMotor : allMotors) {
            intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }


        rightIntake.setDirection(DcMotorSimple.Direction.REVERSE);
    }





    public void

}

package org.firstinspires.ftc.teamcode.statemachineprojectdonttouch.Hardware;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.sax.StartElementListener;
import android.support.annotation.NonNull;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.teamcode.statemachineprojectdonttouch.HelperClasses.Firefly;
import org.firstinspires.ftc.teamcode.Auto.roadrunner.drive.mecanum.SampleMecanumDriveBase;
import org.firstinspires.ftc.teamcode.Auto.roadrunner.util.AxesSigns;
import org.firstinspires.ftc.teamcode.Auto.roadrunner.util.BNO055IMUUtil;
import org.firstinspires.ftc.teamcode.Auto.roadrunner.util.LynxOptimizedI2cFactory;
import org.openftc.revextensions2.ExpansionHubEx;
import org.openftc.revextensions2.ExpansionHubMotor;
import org.openftc.revextensions2.RevBulkData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.firstinspires.ftc.teamcode.Auto.roadrunner.drive.DriveConstants.*;
import static org.firstinspires.ftc.teamcode.HelperClasses.GLOBALS.*;

public class DriveTrain extends SampleMecanumDriveBase {


    private ExpansionHubEx master, slave;
    private Firefly myRobot;
    public ExpansionHubMotor frontLeft, frontRight, backLeft, backRight;
    private BNO055IMU imu;
    private boolean isDebugging = false;
    private ArrayList<ExpansionHubMotor> allMotors = new ArrayList<>();


    public DriveTrain(Firefly myRobot, ArrayList<ExpansionHubMotor> allMotors, BNO055IMU imu, ExpansionHubEx master, ExpansionHubEx slave) {
        super();
        this.myRobot = myRobot;
        frontLeft = allMotors.get(0);
        frontRight = allMotors.get(1);
        backLeft = allMotors.get(2);
        backRight = allMotors.get(3);



        this.imu = imu;
        this.master = master;
        this.slave = slave;


        // TODO: if motors are moving wrong come HEREEEEE
        allMotors.add(frontLeft);
        allMotors.add(backLeft);
        allMotors.add(frontRight);
        allMotors.add(backRight);


        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        if (RUN_USING_ENCODER && MOTOR_VELO_PID != null) {
            setPIDCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, MOTOR_VELO_PID);
        }
    }



    /*
    required methods cuz of roadrunner
     */
    @Override
    public PIDCoefficients getPIDCoefficients(DcMotor.RunMode runMode) {
        PIDFCoefficients coefficients = frontLeft.getPIDFCoefficients(runMode);
        return new PIDCoefficients(coefficients.p, coefficients.i, coefficients.d);
    }

    @Override
    public void setPIDCoefficients(DcMotor.RunMode runMode, PIDCoefficients coefficients) {
        for (ExpansionHubMotor motor : allMotors) {
            motor.setPIDFCoefficients(runMode, new PIDFCoefficients(
                    coefficients.kP, coefficients.kI, coefficients.kD, getMotorVelocityF()
            ));
        }
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        // master = right
        RevBulkData bulkData = master.getBulkInputData();
        RevBulkData slaveData = slave.getBulkInputData();

        if (bulkData == null || slaveData == null) {
            return Arrays.asList(0.0, 0.0, 0.0, 0.0);
        }
        // fl bl fr br
        List<Double> wheelPositions = new ArrayList<>();
        wheelPositions.add(encoderTicksToInches(slaveData.getMotorCurrentPosition(0)));
        wheelPositions.add(encoderTicksToInches(slaveData.getMotorCurrentPosition(1)));
        wheelPositions.add(encoderTicksToInches(bulkData.getMotorCurrentPosition(0)));
        wheelPositions.add(encoderTicksToInches(bulkData.getMotorCurrentPosition(1)));
        return wheelPositions;
    }

    @Override
    public List<Double> getWheelVelocities() {
        RevBulkData masterData = master.getBulkInputData();
        RevBulkData slaveData = slave.getBulkInputData();

        if (masterData == null || slaveData == null) {
            return Arrays.asList(0.0, 0.0, 0.0, 0.0);
        }

        List<Double> wheelVelo = new ArrayList<>();

        wheelVelo.add(encoderTicksToInches(slaveData.getMotorVelocity(0)));
        wheelVelo.add(encoderTicksToInches(slaveData.getMotorVelocity(1)));
        wheelVelo.add(encoderTicksToInches(masterData.getMotorVelocity(0)));
        wheelVelo.add(encoderTicksToInches(masterData.getMotorVelocity(1)));
        return wheelVelo;
    }

    @Override
    public void setMotorPowers(double v, double v1, double v2, double v3) {
        frontLeft.setPower(v);
        backLeft.setPower(v1);
        backRight.setPower(v2);
        frontRight.setPower(v3);
    }

    @Override
    public double getRawExternalHeading() {
        return imu.getAngularOrientation().firstAngle;
    }


    public void startPose(Pose2d pose) {
        setPoseEstimate(pose);
    }






    public void driveMecanum() {

        double rawFL = movementY - movementTurn + movementX * 1.5;
        double rawBL = movementY - movementTurn - movementX * 1.5;
        double rawBR = -movementY - movementTurn - movementX * 1.5;
        double rawFR = -movementY - movementTurn + movementX * 1.5;



        double scaleAmt = 1;
        double biggestPower = Math.abs(rawFL);

        if(Math.abs(rawBL) > (biggestPower)) { biggestPower = rawBL; }
        if(Math.abs(rawFR) > (biggestPower)) { biggestPower = rawFR; }
        if(Math.abs(rawBR) > (biggestPower)) { biggestPower = rawBR; }
        if(biggestPower > 1.0) {
            scaleAmt = 1.0/biggestPower;
        }

        rawFL *= scaleAmt;
        rawFR *= scaleAmt;
        rawBL *= scaleAmt;
        rawBR *= scaleAmt;


        frontLeft.setPower(rawFL);
        frontRight.setPower(rawFR);
        backLeft.setPower(rawBL);
        backRight.setPower(rawBR);
    }
    


    public void setDebugging(boolean debugging) {
        isDebugging = debugging;
    }





    /**
     * so we dont actually need to update/apply movement for our drivetrain since roadrunner takes care of that (?)
     * but we do for teleop so slap this shit in here
     */

    private long loopTime = 0;
    public void updatee() {
        if(SystemClock.uptimeMillis() - loopTime < 16) {
            return;
        }
        loopTime = SystemClock.uptimeMillis();

        driveMecanum(); // the robot will only move if we change movementX, movementY, or movementTurn
    }
}

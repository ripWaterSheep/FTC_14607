package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cCompassSensor;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsUsbDeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import com.acmerobotics.dashboard.*;



/**
 * Created by charliewu on 9/20/18.
 */

@Config
public class HardwareDragonfly {
    /* Public OpMode members. */
    public DcMotor fl   = null;
    public DcMotor  fr  = null;
    public DcMotor bl   = null;
    public DcMotor  br  = null;

    public DcMotorEx arm   = null;
    public DcMotorEx cascade   = null;
    public DcMotor lift   = null;

    public DcMotor intake_motor = null;

    public CRServo intake = null;
    public CRServo intake2 = null;

    public Servo intakeDoor = null;
    public Servo hangRelease = null;
    public Servo markerDeployer = null;
    public Servo hookRelease = null;

    public BNO055IMU revIMU = null;

<<<<<<< Updated upstream
=======
<<<<<<< Updated upstream
=======
    public PIDController pidRotate = null;
    Orientation lastAngles = new Orientation();
    double globalAngle, power = .30, correction, rotation;
>>>>>>> Stashed changes

    //ROBOT CONFIG CONSTANTS
    public static int ARM_LOWERED_VAL = 0;
    public static int ARM_LEVEL_VAL = 743;
    public static int ARM_VERTICAL_VAL = 1905;
    public static int ARM_TRANSITION_VAL = 1000; //1200
    public static int ARM_PARK_VAL = 900;
    public static int ARM_BACK_VAL = 2198;

    public static int ARM_LEFT_GOLD_VAL = 210;
    public static int ARM_CENTER_GOLD_VAL = 162; //202
    public static int ARM_RIGHT_GOLD_VAL = 232;

    public static int ARM_MARKER_DEPLOY_VAL = 560; //675

    public static int LIFT_DOWN_VAL = (int)(0 * 3.69230769231);
    public static int LIFT_MAX_VAL = (int)(-28866 * 3.69230769231);
    public static int LIFT_HOOK_VAL = (int)(-24000 *3.69230769231); //-24273
    public static int LIFT_DETATCH_VAL = (int)(-19500 * 3.69230769231); //-18516; //-19000
    public static int LIFT_CLEAR_VAL = (int)(-14000 *3.69230769231);

    public static int CASCADE_IN_VAL = 0;
    public static int CASCADE_MAX_VAL = -4500;
    public static int CASCADE_SCORE_DEFAULT_VAL = -555; //-560 540 545


    public static int CASCADE_LEFT_GOLD_EXTEND_VAL = -2200; //2250
    public static int CASCADE_CENTER_GOLD_EXTEND_VAL = -1900; //-1997
    public static int CASCADE_RIGHT_GOLD_EXTEND_VAL = -2500; //-2000
    public static int CASCADE_MARKER_EXTEND_VAL = -4500;

    public static int TURN_OUT_DELATCH_VAL = -30; //degrees
    public static int TURN_OUT_RESET_VAL = 0; //degrees

    public static int TURN_CENTER_GOLD_MINADJUST = 5; //degrees

    public static int TURN_OUT_DRIVE_PARK_VAL_1 = -40; //degrees
    public static int FORWARD_MOVE_PARK_VAL_1 = 19; //inches //22 before
    public static int TURN_OUT_DRIVE_PARK_VAL_2 = -110; //degrees //-100
    public static int FORWARD_MOVE_PARK_VAL_2 = 12; //inches //10

    public static int TURN_OUT_DRIVE_DEPLOY_VAL_1 = -60; //degrees
    public static int FORWARD_MOVE_DEPLOY_VAL_1 = 45; //inches
    public static int TURN_OUT_DRIVE_DEPLOY_VAL_2 = -130; //degrees
    public static int FORWARD_MOVE_DEPLOY_VAL_2 = 28; //inches //27
    public static int BACKWARDS_MOVE_DEPLOY_VAL_3 = 12; //inches

    public static int FORWARD_MOVE_SAMPLING_VAL = 13; //inches //15
    public static int FORWARD_MOVE_PUSH_SAMPLING_VAL = 10; //inches //15
    public static int FORWARD_MOVE_MARKER_VAL_EXTRA = 17; //inches //19 before
    public static int BACKWARDS_MOVE_SAMPLING_VAL = 6; //inches
    public static int TURN_LEFT_GOLD_VAL = -30; //degrees
    public static int TURN_RIGHT_GOLD_VAL = 41; //degrees
    public static int FORWARD_MOVE_GOLD_CENTER_PUSH_VAL = 18; //inches
    public static int FORWARD_MOVE_GOLD_SIDE_PUSH_VAL = 20; //inches

            //END ROBOT CONFIG CONSTANTS

<<<<<<< Updated upstream
=======
>>>>>>> Stashed changes
>>>>>>> Stashed changes
//
//    public Servo sv1 = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareDragonfly(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        double sp = 0;

//        sv1 = hwMap.servo.get("servo1");

        // Define and Initialize Motors
        fl   = hwMap.dcMotor.get("fl");
        fr  = hwMap.dcMotor.get("fr");
        bl   = hwMap.dcMotor.get("bl");
        br  = hwMap.dcMotor.get("br");

        arm   = hwMap.get(DcMotorEx.class, "arm");
        lift   = hwMap.dcMotor.get("lift");
        cascade   = hwMap.get(DcMotorEx.class, "cascade");

        intake = hwMap.crservo.get("intake");
        intake2 = hwMap.crservo.get("intake2");

        intake_motor = hwMap.dcMotor.get("intake_motor");

        intakeDoor = hwMap.servo.get("intakeDoor");
        hangRelease = hwMap.servo.get("hangRelease");
        markerDeployer = hwMap.servo.get("markerDeployer");
        hookRelease = hwMap.servo.get("hookRelease");

        revIMU = hwMap.get(BNO055IMU.class, "revIMU");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;
        revIMU.initialize(parameters);

        pidRotate = new PIDController(.003, .00003, 0);

        fl.setPower(sp);
        fr.setPower(sp);
        bl.setPower(sp);
        br.setPower(sp);
//        arm.setPower(sp);
        lift.setPower(sp);
//        cascade.setPower(sp);

        arm.setVelocity(0, AngleUnit.DEGREES);
        cascade.setVelocity(0, AngleUnit.DEGREES);

        cascade.setDirection(DcMotorSimple.Direction.REVERSE);

        intake.setPower(0.1120);
        intake2.setPower(0.1120);

        intakeDoor.setPosition(0);
        hangRelease.setPosition(0.2); // latch hang on start
        markerDeployer.setPosition(0.85);
        hookRelease.setPosition(0.6);

        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        cascade.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cascade.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        intake_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        fr.setDirection(DcMotorSimple.Direction.REVERSE);
//        br.setDirection(DcMotorSimple.Direction.REVERSE);
        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);


    }
//
//    public void turnServoContinuous(double speed){
//        sv1.setPosition(speed);
//    }
//    public void stopServoContinuous(){
//        sv1.setPosition(0);
//    }

    public void resetEncoders()
    {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        cascade.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        arm.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        cascade.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetDriveEncoders()
    {
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
//    public void encoderDrive(double flspeed, double frspeed, double blspeed, double brspeed, int fltarget, int frtarget, int bltarget, int brtarget) {
//        int newFLTarget;
//        int newFRTarget;
//        int newBLTarget;
//        int newBRTarget;
//
//        // Determine new target position, and pass to motor controller
//        newFLTarget = fl.getCurrentPosition()+fltarget;
//        newFRTarget = fr.getCurrentPosition()+frtarget;
//        newBLTarget = bl.getCurrentPosition()+bltarget;
//        newBRTarget = br.getCurrentPosition()+brtarget;
//
//        resetEncoders();
//
//        fl.setPower(flspeed);
//        fr.setPower(frspeed);
//        bl.setPower(blspeed);
//        br.setPower(brspeed);
//
//        // keep looping while we are still active, and there is time left, and both motors are running.
//        while(Math.abs(fl.getCurrentPosition())<Math.abs(fltarget) || Math.abs(fr.getCurrentPosition())<Math.abs(frtarget) || Math.abs(bl.getCurrentPosition())<Math.abs(bltarget) || Math.abs(br.getCurrentPosition())<Math.abs(brtarget))
//        {
//            if(!(Math.abs(fl.getCurrentPosition())<Math.abs(fltarget)))
//            {
//                fl.setPower(0);
//            }
//            if(!(Math.abs(fr.getCurrentPosition())<Math.abs(frtarget)))
//            {
//                fr.setPower(0);
//            }
//            if(!(Math.abs(bl.getCurrentPosition())<Math.abs(bltarget)))
//            {
//                bl.setPower(0);
//            }
//            if(!(Math.abs(br.getCurrentPosition())<Math.abs(brtarget)))
//            {
//                br.setPower(0);
//            }
//        }
//
//        fl.setPower(0);
//        fr.setPower(0);
//        bl.setPower(0);
//        br.setPower(0);
//    }


    public void driveLimitless(double left, double right) {
        fl.setPower(left);
        fr.setPower(right);
        bl.setPower(left);
        br.setPower(right);
    }

    public void allStop()
    {
        fl.setPower(0);
        fr.setPower(0);
        bl.setPower(0);
        br.setPower(0);
    }

    public int getHeading(){
        return -(int)Math.floor(revIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES).thirdAngle);
    }

    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }

    /**
     * Resets the cumulative angle tracking to zero.
     */
    public void resetAngle()
    {
        lastAngles = revIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        globalAngle = 0;
    }

    /**
     * Get current cumulative angle rotation from last reset.
     * @return Angle in degrees. + = left, - = right from zero point.
     */
    public double getAngle()
    {
        // We experimentally determined the Z axis is the axis we want to use for heading angle.
        // We have to process the angle because the imu works in euler angles so the Z axis is
        // returned as 0 to +180 or 0 to -180 rolling back to -179 or +179 when rotation passes
        // 180 degrees. We detect this transition and track the total cumulative angle of rotation.

        Orientation angles = revIMU.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;

        return globalAngle;
    }

}

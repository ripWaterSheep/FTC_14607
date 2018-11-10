package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by charliewu on 9/20/18.
 */

public class HardwareBuggo {
    /* Public OpMode members. */
    public DcMotor spin = null;

    public Servo sv1 = null;

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public HardwareBuggo(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        double sp = 0;

        sv1 = hwMap.servo.get("servo1");

        // Define and Initialize Motors
        spin   = hwMap.dcMotor.get("spin");

        spin.setPower(sp);

        spin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        spin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        spin.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void turnServoContinuous(double speed){
        sv1.setPosition(speed);
    }
    public void stopServoContinuous(){
        sv1.setPosition(0);
    }

    public void resetEncoders()
    {
        spin.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        spin.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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


    public void driveLimitless(double speed) {
        spin.setPower(speed);
    }

    public void allStop()
    {
        spin.setPower(0);
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



}

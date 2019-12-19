package org.firstinspires.ftc.teamcode.test.Auto.RobotUtil;

import org.firstinspires.ftc.teamcode.test.Auto.HelperClasses.Firefly;


import static org.firstinspires.ftc.teamcode.GLOBALS.*;

import static java.lang.Math.PI;

public class RobotPosition {
    public static Firefly robot;

    private static double lastLeft = 0;
    private static double lastRight = 0;
    private static double lastHorizontal = 0;


    public static double worldXPosition = 0;
    public static double worldYPosition = 0;
    public static double worldAngleRad = 0;


    private static double currLeft = 0;
    private static double currRight = 0;
    private static double currHorizontal = 0;



    private static double leftInitialReading = 0;
    private static double rightInitialReading = 0;
    private static double horizontalInitialReading = 0;
    public static double lastResetAngle = 0;



    private static double scale = (PI * 2.3622) / 2048;


    private static double ticksToInches(double ticks) {
        return (ticks/2048) * (PI * 2.3622);
    }


    public static void initPositions(Firefly r, double left, double right, double horizontal) {
        RobotPosition.robot = r;
        currLeft = left;
        currRight = right;
        currHorizontal = horizontal;
    }


    public static void givePositions(double left, double right, double horizontal) {
        currLeft = left;
        currRight = right;
        currHorizontal = horizontal;
    }





    public static void PositioningCalculations() {
        // convert class fields into local vars
        double leftCurrent = currLeft;
        double rightCurrent = -currRight;
        double horizontalCurrent = currHorizontal;


        // find delta distance
        double deltaLeft = leftCurrent - lastLeft;
        double deltaRight = rightCurrent - lastRight;
        double deltaHorizontal = horizontalCurrent - lastHorizontal;



        // now find scaled delta distance (irl distance traveled) and angle
        double scaledDeltaLeft = scale * deltaLeft;
        double scaledDeltaRight = scale * deltaRight;
        double scaledDeltaHorizontal = scale * deltaHorizontal;



       double totalLeftWheel = currLeft - leftInitialReading;
       double totalRightWheel = currRight - rightInitialReading;
       worldAngleRad = AngleWrap(((totalLeftWheel-totalRightWheel) * scale * 2 ) + lastResetAngle);



       double relativeYTraveled = (scaledDeltaLeft + scaledDeltaRight)/2;
       double relativeXTraveled = scaledDeltaHorizontal;


       worldXPosition += (Math.cos(worldAngleRad) * relativeYTraveled) + (Math.sin(worldAngleRad) * relativeXTraveled);
       worldYPosition += (Math.sin(worldAngleRad) * relativeYTraveled) + (Math.cos(worldAngleRad) * relativeXTraveled);


        lastLeft = leftCurrent;
        lastRight = rightCurrent;
        lastHorizontal = horizontalCurrent;
    }





    public static void setPosition(double x, double y, double angle) {
        worldXPosition = x;
        worldYPosition = y;
        worldAngleRad = angle;

        leftInitialReading = currLeft;
        rightInitialReading = currRight;
        horizontalInitialReading = currHorizontal;

    }


}

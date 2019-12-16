package org.firstinspires.ftc.teamcode;


public  class GLOBALS {

    public  final static double flipperHome =  0.15;
    public final  static double flipperOut = 0.8513;
    public  final static double flipperBetween = 0.3;
    public final static double capUp = .9;
    public final static double capBetween = 0.6;
    public final static double capDown = .1;
    public   static double rotaterHome = 0.279;
    public  static double rotaterOut = 0.95;
    public final static double gripperHome = 0.82;
    public final static double gripperGrip = 0.19;


    public  static double P = 15;
    public  static double I = 0.005;
    public  static double D = 6.2045;





    public final static long toMidTime = 450;
    public final static long liftTime = 200;
    public final static long toBackTime = 750;

    public final static long toLiftTimeTo = 400;
    public final static long toBackTimeTo = 700;

    public final static int liftIncrement = -200;
    public final static int liftIncrementer = -500;
    public static double   psuedoHomer = -50;






    public static double movementX = 0;
    public static double movementY = 0;
    public static double movementTurn = 0;



    public static SKYSTONE_POSITION ourSkystonePosition;
    public enum SKYSTONE_POSITION {
        LEFT,
        MIDDLE,
        RIGHT
    }








    public static double AngleWrap(double angle) {
        while(angle <= -Math.PI) {
            angle += 2 * Math.PI;
        }

        while(angle > Math.PI) {
            angle -= 2 * Math.PI;
        }

        return angle;
    }



}

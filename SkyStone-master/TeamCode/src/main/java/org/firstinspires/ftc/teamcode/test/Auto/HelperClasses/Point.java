package org.firstinspires.ftc.teamcode.test.Auto.HelperClasses;

public class Point {
    public double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        x = 0;
        y = 0;
    }



    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point clone() {
        return new Point(x,y);
    }
}

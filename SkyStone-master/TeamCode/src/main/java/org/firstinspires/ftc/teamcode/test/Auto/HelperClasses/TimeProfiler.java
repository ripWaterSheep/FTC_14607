package org.firstinspires.ftc.teamcode.test.Auto.HelperClasses;

import android.os.SystemClock;

// SHAMELESS
public class TimeProfiler {

    private int millisPerClear = 500;
    public TimeProfiler(int millisPerClear){
        this.millisPerClear = millisPerClear;
    }
    public TimeProfiler() {
        millisPerClear = 1000;
    }



    //time fo the start of the interval
    private long updateStartTime = 0;
    private double totalTimeSpentInIntervalSeconds = 0;
    private long totalUpdates = 0;
    private double averageTimePerUpdateMillis = 0;
    private long sampleStartTime = 0;
    /**
     * Marks the start of the interval
     */
    public void markStart(){
        if(SystemClock.uptimeMillis()-sampleStartTime > millisPerClear){
            sampleStartTime = SystemClock.uptimeMillis();
            totalUpdates = 0;
            totalTimeSpentInIntervalSeconds = 0;
        }

        updateStartTime = SystemClock.uptimeMillis();
    }

    /**
     * Marks the end of the interval
     */
    public void markEnd(){
        totalUpdates++;
        double elapsedTime = (SystemClock.uptimeMillis()-updateStartTime)/1000.0;
//        elapsedTime /= 1000000.0;
        totalTimeSpentInIntervalSeconds += elapsedTime;

        averageTimePerUpdateMillis = totalTimeSpentInIntervalSeconds*1000/totalUpdates;
    }


    /**
     * Gets the average time in millis of this profile
     * @return
     */
    public double getAverageTimePerUpdateMillis(){
        return averageTimePerUpdateMillis;
    }

}
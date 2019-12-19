package org.firstinspires.ftc.teamcode.test.Auto.HelperClasses;

import net.frogbots.ftcopmodetunercommon.opmode.TunableOpMode;
import org.firstinspires.ftc.teamcode.test.Auto.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.test.Auto.Hardware.Intake;
import org.openftc.revextensions2.ExpansionHubMotor;

public class Firefly extends TunableOpMode {

    // declare members gachiHYPER

    private DriveTrain ourDriveTrain;
    public Intake ourIntake;



    @Override
    public void init() {
        ExpansionHubMotor ourFrontLeftMotor = hardwareMap.get(ExpansionHubMotor.class, "FL");
        ExpansionHubMotor ourBackLeftMotor = hardwareMap.get(ExpansionHubMotor.class, "BL");
        ExpansionHubMotor ourFrontRightMotor = hardwareMap.get(ExpansionHubMotor.class, "FR");
        ExpansionHubMotor ourBackRightMotor = hardwareMap.get(ExpansionHubMotor.class, "BR");

        ourDriveTrain = new DriveTrain(this, ourFrontLeftMotor, ourBackLeftMotor, ourFrontRightMotor, ourBackRightMotor);



        ExpansionHubMotor ourLeftIntakeMotor = hardwareMap.get(ExpansionHubMotor.class, "leftIntake");
        ExpansionHubMotor ourRightIntakeMotor = hardwareMap.get(ExpansionHubMotor.class, "rightIntake");

        ourIntake = new Intake(this, ourLeftIntakeMotor, ourRightIntakeMotor);


        ourIntake.update();
        ourDriveTrain.update();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }



    TimeProfiler loopProfiler = new TimeProfiler();
    TimeProfiler intakeProfiler = new TimeProfiler();
    TimeProfiler driveTrainMovementProfiler = new TimeProfiler();


    @Override
    public void loop() {
        loopProfiler.markStart();


        driveTrainMovementProfiler.markStart();
        ourDriveTrain.update();
        driveTrainMovementProfiler.markEnd();


        intakeProfiler.markStart();
        ourIntake.update();
        intakeProfiler.markEnd();








        loopProfiler.markEnd();

        addLineSpace();
        telemetry.addLine(header + " FIREFLY TELEMETRY " + header);
        addLineSpace();
        telemetry.addLine("loop profiler: " + loopProfiler.getAverageTimePerUpdateMillis());
        telemetry.addLine("drivetrain profiler: " + driveTrainMovementProfiler.getAverageTimePerUpdateMillis());
        telemetry.addLine("intake profiler: " + intakeProfiler.getAverageTimePerUpdateMillis());
    }









    public void addLineSpace() {
        telemetry.addLine("");
    }

    public static String header = "----------";
}

package org.firstinspires.ftc.teamcode.Auto;

//import com.acmerobotics.dashboard.config.Config;
import android.graphics.drawable.Icon;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.trajectory.constraints.DriveConstraints;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.hardware.motors.*;


/*
 * Constants shared between multiple drive types.
 */
@Config
public class DriveConstants {

    /*
     * TODO: Tune or adjust the following constants to fit your robot. Note that the non-final
     * fields may also be edited through the dashboard (connect to the robot's WiFi network and
     * navigate to https://192.168.49.1:8080/dash). Make sure to save the values here after you
     * adjust them in the dashboard; **config variable changes don't persist between app restarts**.
     */

    private static final MotorConfigurationType MOTOR_CONFIG =
            MotorConfigurationType.getMotorType(GoBILDA5202Series.class);

//    public static final double TICKS_PER_REV = 537.6;

    /*
     * Set the first flag appropriately. If using the built-in motor velocity PID, update
     * MOTOR_VELO_PID with the tuned coefficients from DriveVelocityPIDTuner.
     *
     *
     */

    public static double P_const_v = 30;
    public static double I_const_v = 0; // DEFAULT AT 0.1
    public static double D_const_v = 5; //5

    public static double P_const_t = 0;// 1 2;
    public static double I_const_t = 0; // DEFAULT AT 0.1
    public static double D_const_t = 0;

    public static double P_const_h = 0; // 1
    public static double I_const_h = 0; // DEFAULT AT 0.1
    public static double D_const_h = 0;

    public static final boolean RUN_USING_ENCODER = true;
    public static final PIDCoefficients MOTOR_VELO_PID = new PIDCoefficients(P_const_v, I_const_v,D_const_v);
    public static final PIDCoefficients TRANSLATIONAL_PID = new PIDCoefficients(P_const_t, I_const_t,D_const_t);
    public static final PIDCoefficients HEADING_PID = new PIDCoefficients(P_const_h, I_const_h,D_const_h);
    /*
     * These are physical constants that can be determined from your robot (including the track
     * width; it will be tune empirically later although a rough estimate is important). Users are
     * free to chose whichever linear distance unit they would like so long as it is consistently
     * used. The default values were selected with inches in mind. Road runner uses radians for
     * angular distances although most angular parameters are wrapped in Math.toRadians() for
     * convenience.
     */

    public static double WHEEL_RADIUS = 1.968505;
    public static double GEAR_RATIO = 99.5/19.2; // TODO: TO COMPENSATE FOR +25% ERROR

    public static double TRACK_WIDTH = 14.75; // 13.007 // 10.75 // 14.25 // TODO: CHANGE THIS ONCE TRACK WIDTH CALCULATED

    /*
     * These are the feedforward parameters used to model the drive motor behavior. If you are using
     * the built-in velocity PID, *these values are fine as is*. However, if you do not have drive
     * motor encoders or have elected not to use them for velocity control, these values should be
     * empirically tuned.
     */
    public static double kV = 1.0 / rpmToVelocity(getMaxRpm());//0.01455;// / rpmToVelocity(getMaxRpm());
    public static double kA = 0;//0.00074; // we can leave this tuff untied
    public static double kStatic = 0;//0.08009;

    /*
     * These values are used to generate the trajectories for you robot. To ensure proper operation,
     * the constraints should never exceed ~80% of the robot's actual capabilities. While Road
     * Runner is designed to enable faster autonomous motion, it is a good idea for testing to start
     * small and gradually increase them later after everything is working. The velocity and
     * acceleration values are required, and the jerk values are optional (setting a jerk of 0.0
     * forces acceleration-limited profiling).
     */
    public static DriveConstraints BASE_CONSTRAINTS = new DriveConstraints( // TODO: CHANGE THESE
            40, 20, 0.0, // 40 20 // 40 30
            Math.toRadians(180), Math.toRadians(180), 0.0 //120 120
    );



//    public static double encoderTicksToInches(int ticks) {
//        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
//    }
//
//    public static double inchesToEncoderTicks(double inches) {
//        return (inches * TICKS_PER_REV) / (2 * WHEEL_RADIUS * Math.PI * WHEEL_RADIUS);
//    }
//
//    public static double rpmToVelocity(double rpm) {
//        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
//    }
//
//    public static double getMaxRpm() {
//        return 312;
//    }
//
//    public static double getTicksPerSec() {
//        // note: MotorConfigurationType#getAchieveableMaxTicksPerSecond() isn't quite what we want
//        return (getMaxRpm() * TICKS_PER_REV / 60.0);
//    }
//
//    public static double getMotorVelocityF() {
//        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
//        return 32767 / getTicksPerSec();
//    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / MOTOR_CONFIG.getTicksPerRev();
    }

    public static double rpmToVelocity(double rpm) {
        return rpm * GEAR_RATIO * 2 * Math.PI * WHEEL_RADIUS / 60.0;
    }

    public static double getMaxRpm() {
        return MOTOR_CONFIG.getMaxRPM() *
                (RUN_USING_ENCODER ? MOTOR_CONFIG.getAchieveableMaxRPMFraction() : 1.0);
    }

    public static double getTicksPerSec() {
        // note: MotorConfigurationType#getAchieveableMaxTicksPerSecond() isn't quite what we want
        return (MOTOR_CONFIG.getMaxRPM() * MOTOR_CONFIG.getTicksPerRev() / 60.0);
    }

    public static double getMotorVelocityF() {
        // see https://docs.google.com/document/d/1tyWrXDfMidwYyP_5H4mZyVgaEswhOC35gvdmP-V-5hA/edit#heading=h.61g9ixenznbx
        return 32767 / getTicksPerSec();
    }

}

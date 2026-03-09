package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

  public static class SwerveConstants {
    public static final double MAX_SPEED = Units.feetToMeters(14.7); // Maximum speed in m/s
    public static final double MAX_ANGULAR_SPEED = 2 * Math.PI; 

  }

  public static class OperatorConstants {
    public static final int kOperatorControllerPort = 1;
    public static final int kDriverControllerPort = 0;
    public static final double DEADBAND = 0.05; // Deadband for joystick input.
    public static final int ZERO_GYRO = 4; 
    public static boolean slowMode = false;
    public static final int SLOW_MODE_BUTTON = 2; // Example button for slow mode, need to be changed.

    public static final int SHOOTER_BUTTON = 1;
    public static final int INTAKE_BUTTON = 3;
    public static final int REVERSE_INTAKE_BUTTON = 4;
    
    // -- auto(limelight) -- //
    public static final int ALIGN_BUTTON = 1;
    public static final int ALIGN_RAMP_BUTTON = 3;

    public static final double ALIGN_TOLERANCE_DEGREES = 1.0; 
  }

  public static final class FuelConstants {
    public static final int FUEL_MOTOR1_ID = 31;
    public static final int FUEL_MOTOR2_ID = 32;
    public static final int FUEL_MOTOR3_ID = 33;
    public static final int FUEL_MOTOR_SMART_CURRENT_LIMIT = 40;
    public static final double FUEL_MOTOR_OPEN_LOOP_RAMP_RATE = 0.5;

    // -- Shooter voltage -- //
    // These values will be changed, they're just placeholders for now,
    // we gonna change after we test the shooter
    //and see how much voltage we need to shoot the ball.
    public static final double SHOOTER1_VOLTAGE = 12.0;
    public static final double SHOOTER2_VOLTAGE = 12.0;
    public static final double SHOOTER3_VOLTAGE = 12.0;

    // -- Intake voltage -- //
    // As with the shooter voltage, this is just a placeholder value,
    // we gonna change it after test the intake too.
    public static final double INTAKE_VOLTAGE = 5.0;

    public static final double SHOOTER_DELAY = 1.0; // Delay in seconds
  }
}

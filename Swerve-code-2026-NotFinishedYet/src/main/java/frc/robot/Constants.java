package frc.robot;

import edu.wpi.first.math.util.Units;

public final class Constants {

  public static class SwerveConstants {
    public static final double MAX_SPEED = Units.feetToMeters(4.5); // Maximum speed in m/s
    public static final double MAX_ANGULAR_SPEED = 2 * Math.PI; 

  }

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final double DEADBAND = 0.05; // Deadband for joystick inputs
  }

  public static final class FuelConstants {
    public static final int FUEL_MOTOR1_ID = 10;
    public static final int FUEL_MOTOR2_ID = 20;
    public static final int FUEL_MOTOR3_ID = 30;
    public static final int FUEL_MOTOR_SMART_CURRENT_LIMIT = 40;
    public static final double FUEL_MOTOR_OPEN_LOOP_RAMP_RATE = 0.5;

    public static final double INTAKE_VOLTAGE = 3.0;

    // -- Shooter voltage -- //
    // Ainda será trocado esses valores
    // são apenas para ocupar espaço até eu testar os motores.
    public static final double SHOOTER1_VOLTAGE = 6.0;
    public static final double SHOOTER2_VOLTAGE = 6.0;
    public static final double SHOOTER3_VOLTAGE = 6.0;

    public static final double SHOOTER_DELAY = 1.0; // Delay in seconds
  }
}

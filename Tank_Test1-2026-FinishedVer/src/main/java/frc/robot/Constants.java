package frc.robot;

public final class Constants {
    public static final class DriveConstants {
        // Motor IDs for the left and right drive motors (change as needed)
        public static final int kLeftMotorID = 16;
        public static final int kLeftMotorID2 = 15;
        public static final int kRightMotorID = 14;
        public static final int kRightMotorID2 = 13;

        public static final int kSmartCurrentLimit = 40;
        public static final double kOpenLoopRampRate = 0.5;

        public static final boolean LEFT_INVERTED = true;
        public static final boolean RIGHT_INVERTED = false;

        public static final double DRIVE_SPEED = 0.8; // Multiplier for forward/backward speed
        public static final double TURN_SPEED = 0.7; // Multiplier for turning speed
    }

    public static final class OperatorConstants {

        // -- Controller & DeadBand -- //
        public static final int DRIVER_CONTROLLER_PORT = 0; // Port for the driver controller
        public static final double DEADBAND = 0.05; // Deadband for joystick inputs

        // -- Axis -- //
        // left Analog stick
        public static final int FORWARD_AXIS = 1; // Forward/Backward axis (Y-axis)
        public static final int STRAFE_AXIS = 0; // Strafe axis, if wanted

        // Right Analog stick
        public static final int ROTATION_AXIS = 4; // Rotation axis (X-axis)

        // -- Triggers -- //
        public static final int INTAKE_AXIS = 2; // L2 for intake
        public static final int SHOOTER_AXIS = 3; // R2 for shooting

        // -- Triggers Threshold -- //
        public static final double TRIGGER_THRESHOLD = 0.2; // Threshold for trigger activation
    }

    public static final class FuelConstants {
        // -- Motors IDs -- //
        public static final int FUEL_MOTOR1_ID = 11;
        public static final int FUEL_MOTOR2_ID = 12;

        // -- Current Limit -- //
        public static final int FUEL_MOTOR1_CURRENT_LIMIT = 40; // Current limit for the fuel motors
        public static final int FUEL_MOTOR2_CURRENT_LIMIT = 40; // Current limit for the fuel motors
        public static final double FUEL_OPEN_LOOP_RAMP_RATE = 0.3; // ramp rate for the fuel motors
        // -- Intake Mode -- //
        public static final double INTAKE_MOTOR1_VOLTAGE = 3.0; // Speed for intake
        public static final double INTAKE_MOTOR2_VOLTAGE = 3.0; // Speed for intake
        public static final double FUEL_VOLTAGE = 5.0; // Speed for intake
        public static final double FUEL_REVERSE_INTAKE_VOLTAGE = 5.0; // Speed for reverse intake
        
        // -- Shooter Mode -- //
        public static final double SHOOTER_MOTOR1_VOLTAGE = 9.0; // speed for shooting
        public static final double SHOOTER_MOTOR2_VOLTAGE = 8.0; // speed for shooting
        // -- inversions -- //
        public static final boolean FUEL_MOTOR1_INVERTED = false;
        public static final boolean FUEL_MOTOR2_INVERTED = false;

        // -- Shooter delay -- //
        public static final double SHOOTER_DELAY = 1.0; // time in seconds to wait before shooting
    }
}

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation3d;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ReverseIntakeCommand;
import frc.robot.commands.ShooterCommand;

import frc.robot.subsystems.FuelSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

import swervelib.SwerveInputStream;

public class RobotContainer {

  // -- SUBSYSTEMS -- //

  private final SwerveSubsystem drivebase = new SwerveSubsystem();
  private final FuelSubsystem fuelSubsystem = new FuelSubsystem();
  private final VisionSubsystem visionSubsystem = new VisionSubsystem();

  // -- JOYSTICKS -- //

  public final CommandJoystick operatorJoystick =
      new CommandJoystick(Constants.OperatorConstants.kOperatorControllerPort);

  public final CommandJoystick driverJoystick =
      new CommandJoystick(Constants.OperatorConstants.kDriverControllerPort);

  // -- LIMITERS -- //

  private final SlewRateLimiter xLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter yLimiter = new SlewRateLimiter(3);
  private final SlewRateLimiter rotLimiter = new SlewRateLimiter(6);

  // -- HEADING HOLD PID -- //

  private final PIDController headingPID =
      new PIDController(4.0, 0.0, 0.1);

  private double targetHeading = 0;

  // -- LIMELIGHT AUTO AIM PID -- //

  private final PIDController limelightAimPID =
      new PIDController(0.03, 0.0, 0.002);

  // -- SLOW MODE -- //

  private boolean slowMode = false;

  // -- COMMANDS -- //

  private final IntakeCommand intakeCommand =
      new IntakeCommand(fuelSubsystem);

  private final ReverseIntakeCommand reverseIntakeCommand =
      new ReverseIntakeCommand(fuelSubsystem);

  private final ShooterCommand shooterCommand =
      new ShooterCommand(fuelSubsystem);

  public RobotContainer() {

    if (edu.wpi.first.wpilibj.DriverStation.getAlliance().isPresent()) {

        var alliance = edu.wpi.first.wpilibj.DriverStation.getAlliance().get();

        drivebase.getSwerveDrive().zeroGyro();

        if (alliance == edu.wpi.first.wpilibj.DriverStation.Alliance.Red) {
            drivebase.getSwerveDrive().setGyroOffset(new Rotation3d(0, 0, Math.PI));
        } else {
            drivebase.getSwerveDrive().setGyroOffset(new Rotation3d());
        }
    }

    drivebase.zeroGyro();

    headingPID.enableContinuousInput(-Math.PI, Math.PI);
    headingPID.setTolerance(Math.toRadians(1));

    limelightAimPID.setTolerance(1.0);

    configureBindings();

    drivebase.setDefaultCommand(
        drivebase.driveFieldOriented(driveWithAssist));
  }

  // -- BUTTON BINDINGS -- //

  private void configureBindings() {

    operatorJoystick.button(Constants.OperatorConstants.INTAKE_BUTTON)
        .whileTrue(intakeCommand);

    operatorJoystick.button(Constants.OperatorConstants.REVERSE_INTAKE_BUTTON)
        .whileTrue(reverseIntakeCommand);

    operatorJoystick.button(Constants.OperatorConstants.SHOOTER_BUTTON)
            .whileTrue(shooterCommand);
    
    driverJoystick.button(Constants.OperatorConstants.ZERO_GYRO)
        .onTrue(
            Commands.runOnce(() ->
                drivebase.zeroGyro())
        );

    driverJoystick.button(Constants.OperatorConstants.SLOW_MODE_BUTTON)
        .onTrue(
            Commands.runOnce(() -> slowMode = !slowMode)
        );
  }

  // -- DRIVE WITH ASSIST -- //

  SwerveInputStream driveWithAssist =
      SwerveInputStream.of(
          drivebase.getSwerveDrive(),

          () -> {

            double input =
                MathUtil.applyDeadband(
                    driverJoystick.getRawAxis(1),
                    Constants.OperatorConstants.DEADBAND);

            input = Math.copySign(input * input, input);

            if (slowMode) input *= 0.35;

            return xLimiter.calculate(input * -1);
          },

          () -> {

            double input =
                MathUtil.applyDeadband(
                    driverJoystick.getRawAxis(0),
                    Constants.OperatorConstants.DEADBAND);

            input = Math.copySign(input * input, input);

            if (slowMode) input *= 0.35;

            return yLimiter.calculate(input * -1);
          }
      )

      .withControllerRotationAxis(() -> {

        double manualRotation =
            MathUtil.applyDeadband(driverJoystick.getRawAxis(2), 0.05);

        boolean alignPressed =
            driverJoystick.button(Constants.OperatorConstants.ALIGN_BUTTON)
                .getAsBoolean();

        Pose2d robotPose = drivebase.getPose();
        double currentHeading = robotPose.getRotation().getRadians();

        // -- DRIVER ROTATION -- //

        if (Math.abs(manualRotation) > 0.15) {

          targetHeading = currentHeading;

          return rotLimiter.calculate(manualRotation * -1);
        }

        // -- AUTO AIM -- //

        if (alignPressed && visionSubsystem.hasTarget()) {

          double tx = visionSubsystem.getTX();

          double output =
              limelightAimPID.calculate(tx, 0);

          output = MathUtil.clamp(output, -0.6, 0.6);

          return rotLimiter.calculate(output);
        }

        // -- HEADING HOLD -- //

        double output =
            headingPID.calculate(currentHeading, targetHeading);

        output = MathUtil.clamp(output, -0.4, 0.4);

        return rotLimiter.calculate(output);
      })

      .deadband(Constants.OperatorConstants.DEADBAND)
      .scaleTranslation(0.8)
      .scaleRotation(1.0)
      .allianceRelativeControl(true);

  public Command getAutonomousCommand() {
    return null;
  }
}
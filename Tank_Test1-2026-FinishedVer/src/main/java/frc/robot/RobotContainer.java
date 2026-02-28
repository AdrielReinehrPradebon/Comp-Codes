package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ReverseIntakeCommand;
import frc.robot.commands.ShooterCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FuelSubsystem;

public class RobotContainer {

    private final FuelSubsystem fuelSubsystem = new FuelSubsystem();
    public final DriveSubsystem driveSubsystem = new DriveSubsystem();

    private final CommandPS4Controller driverController =
            new CommandPS4Controller(OperatorConstants.DRIVER_CONTROLLER_PORT);

    private final DriveCommand driveCommand =
            new DriveCommand(driveSubsystem, driverController);

    private final IntakeCommand intakeCommand =
            new IntakeCommand(fuelSubsystem, driverController);

    private final ReverseIntakeCommand reverseIntakeCommand =
            new ReverseIntakeCommand(fuelSubsystem);

    private final ShooterCommand shooterCommand =
            new ShooterCommand(fuelSubsystem);

    public RobotContainer() {

        driveSubsystem.setDefaultCommand(driveCommand);

        driverController.R2().whileTrue(intakeCommand);
        driverController.R1().whileTrue(reverseIntakeCommand);

        driverController.L2().whileTrue(shooterCommand);
    }

    public Command getAutonomousCommand() {
        return null;
    }
}

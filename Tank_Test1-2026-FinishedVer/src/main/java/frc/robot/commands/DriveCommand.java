package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;

import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {

    private final DriveSubsystem drive;
    private final CommandPS4Controller controller;


    public DriveCommand(DriveSubsystem subsystem,
                        CommandPS4Controller controller) {

        this.drive = subsystem;
        this.controller = controller;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {

        double forward = controller.getLeftY();
        double rotation = -controller.getRightX();

        forward = MathUtil.applyDeadband(
                forward,
                Constants.OperatorConstants.DEADBAND
        );

        rotation = MathUtil.applyDeadband(
                rotation,
                Constants.OperatorConstants.DEADBAND
        );

        drive.arcadeDrive(forward, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        drive.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Constants;
import frc.robot.subsystems.FuelSubsystem;

public class IntakeCommand extends Command {

    private final FuelSubsystem fuelSubsystem;
    private final CommandPS4Controller controller;

    public IntakeCommand(FuelSubsystem fuelSubsystem,
                         CommandPS4Controller controller) {

        this.fuelSubsystem = fuelSubsystem;
        this.controller = controller;

        addRequirements(fuelSubsystem);
    }

    @Override
    public void execute() {

        double trigger = controller.getR2Axis();

        trigger = MathUtil.applyDeadband(
                trigger,
                Constants.OperatorConstants.DEADBAND
        );

        trigger = MathUtil.clamp(trigger, 0.0, 1.0);

        fuelSubsystem.intake(trigger);
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

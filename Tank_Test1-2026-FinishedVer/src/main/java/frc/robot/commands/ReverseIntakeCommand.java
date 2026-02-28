package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.FuelSubsystem;

public class ReverseIntakeCommand extends Command {

    private final FuelSubsystem fuelSubsystem;

    public ReverseIntakeCommand(FuelSubsystem fuelSubsystem) {

        this.fuelSubsystem = fuelSubsystem;

        addRequirements(fuelSubsystem);
    }

    @Override
    public void execute() {

        // double trigger = controller.getR1Axis();

        // trigger = MathUtil.applyDeadband(
        //         trigger,
        //         Constants.OperatorConstants.DEADBAND
        // );

        // trigger = MathUtil.clamp(trigger, 0.0, 1.0);

        fuelSubsystem.reverseIntake(Constants.FuelConstants.FUEL_REVERSE_INTAKE_VOLTAGE);
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

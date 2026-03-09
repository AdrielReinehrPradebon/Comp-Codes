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
        fuelSubsystem.reverseIntake(Constants.FuelConstants.INTAKE_VOLTAGE);
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

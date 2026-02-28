package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.FuelSubsystem;

public class ShooterCommand extends Command {
    private final FuelSubsystem fuelSubsystem;

    private final Timer timer = new Timer();

    public ShooterCommand(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        timer.start();
    }

    @Override
    public void execute() {
        fuelSubsystem.spinShooter1(Constants.FuelConstants.SHOOTER_MOTOR1_VOLTAGE);

        if (timer.get() >= Constants.FuelConstants.SHOOTER_DELAY) {
            fuelSubsystem.spinShooter2(Constants.FuelConstants.SHOOTER_MOTOR2_VOLTAGE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.stop(); // Stop the shooter when the command ends
        timer.stop();
    }

    @Override
    public boolean isFinished() {
        return false; // This command runs until interrupted
    }
}

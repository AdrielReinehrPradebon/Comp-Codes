package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.Constants;

public class ShooterCommand extends Command {

    private final FuelSubsystem fuelSubsystem;
    private final Timer timer = new Timer();

    public ShooterCommand(FuelSubsystem fuelSubsystem) {
        this.fuelSubsystem = fuelSubsystem;
        addRequirements(fuelSubsystem);
    }

    @Override
    public void initialize(){
        timer.reset();
        timer.start();

        fuelSubsystem.spinShooter3(Constants.FuelConstants.SHOOTER3_VOLTAGE);
        fuelSubsystem.spinShooter1(Constants.FuelConstants.SHOOTER1_VOLTAGE);
    }
    
    @Override
    public void execute() {

        if (timer.get() >= Constants.FuelConstants.SHOOTER_DELAY) {
            fuelSubsystem.spinShooter2(Constants.FuelConstants.SHOOTER2_VOLTAGE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        
        timer.stop();
        fuelSubsystem.stop();
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}    

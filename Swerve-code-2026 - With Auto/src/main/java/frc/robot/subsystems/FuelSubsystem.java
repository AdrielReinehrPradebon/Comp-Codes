package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FuelSubsystem extends SubsystemBase {
    private final SparkMax fuelMotor1 = 
        new  SparkMax(Constants.FuelConstants.FUEL_MOTOR1_ID, MotorType.kBrushless);

    private final SparkMax fuelMotor2 = 
        new SparkMax(Constants.FuelConstants.FUEL_MOTOR2_ID, MotorType.kBrushless);

    private final SparkMax fuelMotor3 = 
        new SparkMax(Constants.FuelConstants.FUEL_MOTOR3_ID, MotorType.kBrushless);

    public FuelSubsystem() {

        SparkMaxConfig config = new SparkMaxConfig(); 
        config.smartCurrentLimit(Constants.FuelConstants.FUEL_MOTOR_SMART_CURRENT_LIMIT);
        config.idleMode(IdleMode.kCoast);
        config.openLoopRampRate(Constants.FuelConstants.FUEL_MOTOR_OPEN_LOOP_RAMP_RATE);

        fuelMotor1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        fuelMotor2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        fuelMotor3.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void reverseIntake(double voltage) {
        fuelMotor1.setVoltage(-voltage);
        fuelMotor2.setVoltage(voltage);
    }

    public void intake(double voltage) {
        fuelMotor1.setVoltage(voltage);
        fuelMotor2.setVoltage(-voltage);
    }
    public void spinShooter1(double voltage) {
            fuelMotor1.setVoltage(voltage);
    }

    public void spinShooter2(double voltage) {
        fuelMotor2.setVoltage(voltage);
    }

    public void spinShooter3(double voltage) {
        fuelMotor3.setVoltage(-voltage);
    }

    public void stop() {
        fuelMotor1.stopMotor();
        fuelMotor2.stopMotor();
        fuelMotor3.stopMotor(); 
    }

}

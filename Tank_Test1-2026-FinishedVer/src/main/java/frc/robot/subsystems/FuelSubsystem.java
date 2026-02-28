package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class FuelSubsystem extends SubsystemBase {

    private final SparkMax fuelMotor1 =
            new SparkMax(Constants.FuelConstants.FUEL_MOTOR1_ID, MotorType.kBrushless);

    private final SparkMax fuelMotor2 =
            new SparkMax(Constants.FuelConstants.FUEL_MOTOR2_ID, MotorType.kBrushless);

    public FuelSubsystem() {

        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(Constants.FuelConstants.FUEL_MOTOR1_CURRENT_LIMIT);
        config.idleMode(IdleMode.kCoast);
        config.openLoopRampRate(Constants.FuelConstants.FUEL_OPEN_LOOP_RAMP_RATE);

        fuelMotor1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        fuelMotor2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    }

    public void intake(double percentage) {

        percentage = MathUtil.clamp(percentage, 0.0, 1.0); 
        double voltage = percentage * Constants.FuelConstants.FUEL_VOLTAGE; 
        fuelMotor1.setVoltage(voltage); fuelMotor2.setVoltage(-voltage);   
    }

    public void reverseIntake(double percentage) {

        percentage = MathUtil.clamp(percentage, 0.0, 1.0); 
        double voltage = percentage * Constants.FuelConstants.FUEL_VOLTAGE; 
        fuelMotor1.setVoltage(-voltage); fuelMotor2.setVoltage(voltage);   
    }

    public void spinShooter1(double voltage) {
        fuelMotor1.setVoltage(voltage);
    }

    public void spinShooter2(double voltage) {
        fuelMotor2.setVoltage(voltage);
    }

    public void stop() {
        fuelMotor1.stopMotor();
        fuelMotor2.stopMotor();
    }
}

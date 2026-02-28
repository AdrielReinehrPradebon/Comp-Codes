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
    private final SparkMax fuelMotor1 = new  SparkMax(Constants.FuelConstants.FUEL_MOTOR1_ID, MotorType.kBrushless);
    private final SparkMax fuelMotor2 = new SparkMax(Constants.FuelConstants.FUEL_MOTOR2_ID, MotorType.kBrushless);
    private final SparkMax fuelMotor3 = new SparkMax(Constants.FuelConstants.FUEL_MOTOR3_ID, MotorType.kBrushless);

    public FuelSubsystem() {

        SparkMaxConfig config = new SparkMaxConfig(); 
        config.smartCurrentLimit(Constants.FuelConstants.FUEL_MOTOR_SMART_CURRENT_LIMIT);
        config.idleMode(IdleMode.kCoast);
        config.openLoopRampRate(Constants.FuelConstants.FUEL_MOTOR_OPEN_LOOP_RAMP_RATE);

        fuelMotor1.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        fuelMotor2.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        fuelMotor3.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void intake(double percentage) {
            percentage = MathUtil.clamp(percentage, 0.0, 1.0);
            double voltage = percentage * Constants.FuelConstants.INTAKE_VOLTAGE;
            fuelMotor1.setVoltage(voltage);
            fuelMotor2.setVoltage(voltage);
            fuelMotor3.setVoltage(voltage);
    }
    // fiz apenas esse modelo, irei fazer igual o sistema do tank
    // primeiro rodar o motor e depois girar os outros que levan a bola.
    public void spinShooter1() {
        fuelMotor1.setVoltage(Constants.FuelConstants.SHOOTER1_VOLTAGE); // need to be changed
    }

    public void spinShooter2() {
        fuelMotor2.setVoltage(Constants.FuelConstants.SHOOTER2_VOLTAGE); // need to be changed
    }

    public void spinShooter3() {
        fuelMotor3.setVoltage(Constants.FuelConstants.SHOOTER3_VOLTAGE); // need to be changed
    }

    public void stop() {
        fuelMotor1.setVoltage(0);
        fuelMotor2.setVoltage(0);
        fuelMotor3.setVoltage(0); 
    }

}

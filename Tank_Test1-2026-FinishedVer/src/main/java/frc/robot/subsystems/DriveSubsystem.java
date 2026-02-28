package frc.robot.subsystems;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax rightLeader =
            new SparkMax(Constants.DriveConstants.kRightMotorID,
                    SparkMax.MotorType.kBrushed);

    private final SparkMax leftLeader =
            new SparkMax(Constants.DriveConstants.kLeftMotorID,
                    SparkMax.MotorType.kBrushed);

    private final SparkMax rightFollower =
            new SparkMax(Constants.DriveConstants.kRightMotorID2,
                    SparkMax.MotorType.kBrushed);

    private final SparkMax leftFollower =
            new SparkMax(Constants.DriveConstants.kLeftMotorID2,
                    SparkMax.MotorType.kBrushed);

    private final DifferentialDrive drive =
            new DifferentialDrive(leftLeader, rightLeader);

    public DriveSubsystem() {

        drive.setSafetyEnabled(false);

        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(Constants.DriveConstants.kSmartCurrentLimit);
        config.openLoopRampRate(Constants.DriveConstants.kOpenLoopRampRate);
        config.idleMode(IdleMode.kBrake);

        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        SparkMaxConfig followerConfig = new SparkMaxConfig();
        followerConfig.follow(leftLeader);
        leftFollower.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        followerConfig = new SparkMaxConfig();
        followerConfig.follow(rightLeader);
        rightFollower.configure(followerConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void arcadeDrive(double forward, double rotation) {

        drive.arcadeDrive(
                forward * Constants.DriveConstants.DRIVE_SPEED,
                rotation * Constants.DriveConstants.TURN_SPEED
        );
    }
}

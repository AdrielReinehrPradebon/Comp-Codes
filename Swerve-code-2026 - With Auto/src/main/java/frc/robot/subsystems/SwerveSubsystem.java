package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.io.File;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.NetworkTableInstance;
import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;

public class SwerveSubsystem extends SubsystemBase {
    File directory = new File(Filesystem.getDeployDirectory(), "swerve");
    SwerveDrive swerveDrive;

    public SwerveSubsystem() {
        try
        {
            swerveDrive = new SwerveParser(directory).createSwerveDrive(Constants.SwerveConstants.MAX_SPEED, new Pose2d());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        swerveDrive.setHeadingCorrection(false);
    }

    public SwerveDrive getSwerveDrive() {
        return swerveDrive;
    }
    public void driveFieldOriented(ChassisSpeeds velocity) {
        swerveDrive.driveFieldOriented(velocity);
    }


    public Command driveFieldOriented(Supplier<ChassisSpeeds> velocity) {
        return run(() -> {
            swerveDrive.driveFieldOriented(velocity.get());
        });
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public void zeroGyro() {
        swerveDrive.zeroGyro();
    }

    @Override
    public void periodic() {

        if (NetworkTableInstance.getDefault()
            .getTable("limelight")
            .getEntry("tv")
            .getDouble(0) == 1) {
        
                double botpose[] =
                    NetworkTableInstance.getDefault()
                        .getTable("limelight")
                        .getEntry("botpose_wpiblue")
                        .getDoubleArray(new double [6]);

                Pose2d visionPose =
                    new Pose2d(botpose[0], botpose[1],
                    Rotation2d.fromDegrees(botpose[5]));
                    
                swerveDrive.addVisionMeasurement(
                    visionPose,
                    Timer.getFPGATimestamp());
        }
                

    SmartDashboard.putNumber("FL angle",
        swerveDrive.getModules()[0].getAbsoluteEncoder().getAbsolutePosition());

    SmartDashboard.putNumber("FR angle",
        swerveDrive.getModules()[1].getAbsoluteEncoder().getAbsolutePosition());

    SmartDashboard.putNumber("BL angle",
        swerveDrive.getModules()[2].getAbsoluteEncoder().getAbsolutePosition());

    SmartDashboard.putNumber("BR angle",
        swerveDrive.getModules()[3].getAbsoluteEncoder().getAbsolutePosition());
    }
}
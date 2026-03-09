package frc.robot.subsystems;

import java.util.Set;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class VisionSubsystem extends SubsystemBase{
    
    private final AprilTagFieldLayout fieldLayout = 
        AprilTagFields.kDefaultField.loadAprilTagLayoutField();

    private static final Set<Integer> BASKET_TAGS = Set.of(1, 2); // Example tag IDs for the basket.

    public Pose2d getTagPose2d(int tagID) {
        return fieldLayout.getTagPose(tagID)
                .map(p -> p.toPose2d())
                .orElse(null);
    }

    public Pose2d getBasketTargetPose() {
        if (!hasTarget() || !isBasketTag()) return null;

        return getTagPose2d(getTagID());
    }
    
    public Pose2d getRampTargetPose(double offsetMeters) {
        int id = getTagID();

        if (!isBasketTag())  return null;

        Pose2d tagPose = getTagPose2d(id);
        
        // Need to be defined the center of the basket, placeholder values.
        Pose2d basketCenter = new Pose2d(0.0, 0.0, new Rotation2d());
        
        Translation2d direction =
            basketCenter.getTranslation()
                .minus(tagPose.getTranslation());

        direction = direction.div(direction.getNorm());

        Translation2d rampPoint =
            tagPose.getTranslation()
                .plus(direction.times(offsetMeters));

        return new Pose2d(
            rampPoint,
            tagPose.getRotation()
        );
    }

    public boolean hasTarget() {
        return NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tv")
                .getDouble(0) == 1;
    }
    
    public double getTX() {
        return NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tx")
                .getDouble(0.0);
    }

    public int getTagID() {
        return(int) NetworkTableInstance.getDefault()
                .getTable("limelight")
                .getEntry("tid")
                .getDouble(-1);
    }

    public boolean isBasketTag() {
        return BASKET_TAGS.contains(getTagID());
    }
}

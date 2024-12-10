package frc.robot.POM_lib;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveWheelPositions;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTagCamera;
import org.photonvision.EstimatedRobotPose;

import java.util.Optional;

public class POMPoseEstimator {
    POMAprilTagCamera[] cameras;
    SwerveDrivePoseEstimator mainEstiator;

    public POMPoseEstimator(POMAprilTagCamera[] cameras, SwerveDrivePoseEstimator mainEstimator) {
        this.cameras = cameras;
        this.mainEstiator = mainEstimator;
    }

    public Pose2d getPose() {
        return mainEstiator.getEstimatedPosition();
    }

    public void update(SwerveDriveWheelPositions positions, Rotation2d rotation2d) {
        for (POMAprilTagCamera cam : cameras) {
            Optional<EstimatedRobotPose> estimatedRobotPose = cam.getEstimatedPose();
            estimatedRobotPose.ifPresent(robotPose ->
                    mainEstiator.addVisionMeasurement(robotPose.estimatedPose.toPose2d(), robotPose.timestampSeconds));
        }
        mainEstiator.update(rotation2d, positions);
    }
}

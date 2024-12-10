package frc.robot.POM_lib;

import edu.wpi.first.math.estimator.SwerveDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.kinematics.SwerveDriveWheelPositions;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTagCamera;
import org.photonvision.EstimatedRobotPose;

import java.util.Optional;

public class POMPoseEstimator {
    POMAprilTagCamera[] cameras;
    SwerveDrivePoseEstimator mainEstimator;

    public POMPoseEstimator(POMAprilTagCamera[] cameras, SwerveDrivePoseEstimator mainEstimator) {
        this.cameras = cameras;
        this.mainEstimator = mainEstimator;
    }

    public Pose2d getPose() {
        return mainEstimator.getEstimatedPosition();
    }

    public Translation3d getDistFrom(Pose3d other) {
        return other.minus(new Pose3d(getPose())).getTranslation();
    }

    public void update(SwerveDriveWheelPositions positions, Rotation2d rotation2d) {
        for (POMAprilTagCamera cam : cameras) {
            Optional<EstimatedRobotPose> estimatedRobotPose = cam.getEstimatedPose();
            estimatedRobotPose.ifPresent(robotPose ->
                    mainEstimator.addVisionMeasurement(robotPose.estimatedPose.toPose2d(), robotPose.timestampSeconds));
        }
        mainEstimator.update(rotation2d, positions);
    }
}

package frc.robot.POM_lib.Vision.AprilTag;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Transform3d;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;

import java.io.IOException;

public class AprilTagMath {
    public static Transform3d getTagToRobot(PhotonPipelineResult cameraToTag, Transform3d robotToCamera) throws IOException {
        PhotonPoseEstimator photonPoseEstimator = new PhotonPoseEstimator(AprilTagFieldLayout.loadFromResource(
                AprilTagFields.kBaseResourceDir),
                PhotonPoseEstimator.PoseStrategy.AVERAGE_BEST_TARGETS,
                robotToCamera);
        photonPoseEstimator.update(cameraToTag);
        return photonPoseEstimator.getRobotToCameraTransform();
    }
}

package frc.robot.POM_lib.Vision.AprilTag;

import edu.wpi.first.apriltag.AprilTagFieldLayout;
import edu.wpi.first.apriltag.AprilTagFields;
import edu.wpi.first.math.geometry.Transform3d;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPoseEstimator;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class POMAprilTagCamera extends PhotonCamera {
    Transform3d cameraToRobot;
    PhotonPoseEstimator photonPoseEstimator;

    public POMAprilTagCamera(String name, Transform3d cameraToRobot) throws IOException {
        super(name);
        photonPoseEstimator = new PhotonPoseEstimator(AprilTagFieldLayout.loadFromResource(
                AprilTagFields.kBaseResourceDir),
                PhotonPoseEstimator.PoseStrategy.MULTI_TAG_PNP_ON_COPROCESSOR,
                cameraToRobot);
    }

    public List<POMAprilTag> getListOfVisibleTags() throws IOException {
        List<POMAprilTag> list = new ArrayList<>();
        PhotonPipelineResult pipelineResult = super.getLatestResult();
        for (PhotonTrackedTarget trackedTarget : pipelineResult.getTargets()) {
            list.add(new POMAprilTag(trackedTarget.getFiducialId()));
        }
        return list;
    }

    public Optional<EstimatedRobotPose> getEstimatedPose() {
        return photonPoseEstimator.update();
    }

    public POMAprilTag getCertainTag(int desiredID) throws IOException {
        for (POMAprilTag tag : getListOfVisibleTags()) {
            if (tag.id == desiredID) return tag;
        }
        return null;
    }

    public boolean isCertainTagVisible(int desiredID) throws IOException {
        for (POMAprilTag tag : getListOfVisibleTags()) {
            if (tag.id == desiredID) return true;
        }
        return false;
    }
}

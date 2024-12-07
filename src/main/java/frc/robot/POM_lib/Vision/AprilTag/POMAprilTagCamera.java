package frc.robot.POM_lib.Vision.AprilTag;

import edu.wpi.first.math.geometry.Transform3d;
import frc.robot.POM_lib.Vision.POMCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class POMAprilTagCamera extends POMCamera {
    Transform3d cameraToRobot;

    public POMAprilTagCamera(String name, Transform3d cameraToRobot) {
        super(name);
        this.cameraToRobot = cameraToRobot;
    }

    public List<POMAprilTag> getListOfVisibleTags() throws IOException {
        // TODO
        List<POMAprilTag> list = new ArrayList<>();
        PhotonPipelineResult pipelineResult = photonCamera.getLatestResult();
        for (PhotonTrackedTarget trackedTarget : pipelineResult.getTargets()) {
            list.add(new POMAprilTag(trackedTarget.getBestCameraToTarget().getTranslation(),
                    AprilTagMath.getTagToRobot(pipelineResult, cameraToRobot).getTranslation(),
                    trackedTarget.getFiducialId()));
        }
        return list;
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

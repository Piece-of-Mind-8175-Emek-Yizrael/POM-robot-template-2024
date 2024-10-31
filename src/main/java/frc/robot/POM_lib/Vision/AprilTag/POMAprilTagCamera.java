package frc.robot.POM_lib.Vision.AprilTag;

import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.POM_lib.Vision.POMCamera;

import java.util.List;

public class POMAprilTagCamera extends POMCamera {
    Translation3d cameraToRobot;

    public POMAprilTagCamera(String name, Translation3d cameraToRobot) {
        super(name);
        this.cameraToRobot = cameraToRobot;
    }

    public List<POMAprilTag> getListOfVisibleTags() {
        return null;
        // TODO
    }

    public POMAprilTag getCertainTag(int desiredID) {
        for (POMAprilTag tag : getListOfVisibleTags()) {
            if (tag.id == desiredID) return tag;
        }
        return null;
    }

    public boolean isCertainTagVisible(int desiredID) {
        for (POMAprilTag tag : getListOfVisibleTags()) {
            if (tag.id == desiredID) return true;
        }
        return false;
    }
}

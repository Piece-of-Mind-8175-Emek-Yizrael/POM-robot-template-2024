package frc.robot.POM_lib.Vision.AprilTag;

import edu.wpi.first.math.geometry.Translation3d;


public class POMAprilTag {
    Translation3d cameraToTag, robotToTag;
    int id;

    public POMAprilTag(Translation3d cameraToTag, Translation3d robotToTag, int id) {
        this.cameraToTag = cameraToTag;
        this.robotToTag = robotToTag;
        this.id = id;
    }

    public Translation3d getCameraToTag() {
        return cameraToTag;
    }

    public void setCameraToTag(Translation3d cameraToTag) {
        this.cameraToTag = cameraToTag;
    }

    public Translation3d getRobotToTag() {
        return robotToTag;
    }

    public void setRobotToTag(Translation3d robotToTag) {
        this.robotToTag = robotToTag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package frc.robot.POM_lib.Vision.examples;

import edu.wpi.first.math.geometry.Transform3d;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTagCamera;
import frc.robot.POM_lib.Vision.ObjectDetection.POMDetectedObject;
import frc.robot.POM_lib.Vision.ObjectDetection.POMDetectedObjectGroup;
import frc.robot.POM_lib.Vision.ObjectDetection.POMObjectDetectionCamera;
import frc.robot.POM_lib.Vision.ObjectDetection.POMObjectDetectionCameraConfiguration;

import java.io.IOException;

public class VisionHandlerExample {
    static VisionHandlerExample instance;
    POMObjectDetectionCamera objectDetectionCamera;
    POMAprilTagCamera aprilTagCamera;

    VisionHandlerExample() {
        objectDetectionCamera = new POMObjectDetectionCamera("test"
                , new POMObjectDetectionCameraConfiguration(640, 480));
        aprilTagCamera = new POMAprilTagCamera("testAprilTag",
                new Transform3d());
    }

    public static VisionHandlerExample getInstance() {
        if (instance != null) {
            return instance;
        } else {
            instance = new VisionHandlerExample();
            return instance;
        }
    }

    public void setObjectSortingOrder(POMDetectedObjectGroup.ObjectSortingOrder order) {
        objectDetectionCamera.setSortingOrder(order);
    }

    public boolean isCertainTagVisible(int tagID) throws IOException {
        return aprilTagCamera.isCertainTagVisible(tagID);
    }

    public POMDetectedObject getBestDetectedObject() {
        return objectDetectionCamera.getBestObject();
    }
}

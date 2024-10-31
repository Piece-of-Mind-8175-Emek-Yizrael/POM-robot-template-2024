package frc.robot.POM_lib.Vision.examples;

import edu.wpi.first.math.geometry.Translation3d;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTagCamera;
import frc.robot.POM_lib.Vision.ObjectDetection.POMDetectedObject;
import frc.robot.POM_lib.Vision.ObjectDetection.POMDetectedObjectGroup;
import frc.robot.POM_lib.Vision.ObjectDetection.POMObjectDetectionCamera;
import frc.robot.POM_lib.Vision.ObjectDetection.POMObjectDetectionCameraConfiguration;

public class VisionHandlerExample {
    static VisionHandlerExample instance;
    POMObjectDetectionCamera objectDetectionCamera;
    POMAprilTagCamera aprilTagCamera;

    VisionHandlerExample() {
        objectDetectionCamera = new POMObjectDetectionCamera("testObjectDetection"
                , new POMObjectDetectionCameraConfiguration(224, 224));
        aprilTagCamera = new POMAprilTagCamera("testAprilTag",
                new Translation3d());
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

    public boolean isCertainTagVisible(int tagID) {
        return aprilTagCamera.isCertainTagVisible(tagID);
    }

    public POMDetectedObject getBestDetectedObject() {
        return objectDetectionCamera.getBestObject();
    }
}

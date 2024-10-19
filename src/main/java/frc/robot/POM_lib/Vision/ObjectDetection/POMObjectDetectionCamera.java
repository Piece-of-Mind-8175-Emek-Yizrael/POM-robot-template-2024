package frc.robot.POM_lib.Vision.ObjectDetection;

import frc.robot.POM_lib.Vision.POMCamera;

public class POMObjectDetectionCamera extends POMCamera {
    POMObjectDetectionCameraConfiguration configuration;

    public POMObjectDetectionCamera(String name, POMObjectDetectionCameraConfiguration configuration) {
        super(name);
        this.configuration = configuration;
    }

    public POMDetectedObjectGroup getDetectionGroup() {
        return new POMDetectedObjectGroup(photonCamera.getLatestResult()
                , POMDetectedObjectGroup.ObjectSortingOrder.Y_Closest
                , configuration);
    }

    public POMDetectedObject getBestObject() {
        return getDetectionGroup().getBestObject();
    }
}

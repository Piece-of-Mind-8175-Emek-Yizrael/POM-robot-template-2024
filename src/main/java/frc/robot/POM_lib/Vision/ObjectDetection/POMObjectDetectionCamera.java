package frc.robot.POM_lib.Vision.ObjectDetection;

import frc.robot.POM_lib.Vision.POMCamera;

public class POMObjectDetectionCamera extends POMCamera {
    POMObjectDetectionCameraConfiguration configuration;
    POMDetectedObjectGroup.ObjectSortingOrder sortingOrder;

    public POMObjectDetectionCamera(String name, POMObjectDetectionCameraConfiguration configuration) {
        super(name);
        this.configuration = configuration;
        setSortingOrder(POMDetectedObjectGroup.ObjectSortingOrder.Y_Closest);
    }

    public void setSortingOrder(POMDetectedObjectGroup.ObjectSortingOrder sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public POMDetectedObjectGroup getDetectionGroup() {
        return new POMDetectedObjectGroup(photonCamera.getLatestResult()
                , sortingOrder
                , configuration);
    }

    public POMDetectedObject getBestObject() {
        return getDetectionGroup().getBestObject();
    }
}


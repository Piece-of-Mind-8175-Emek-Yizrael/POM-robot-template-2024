package frc.robot.POM_lib.Vision.ObjectDetection;

import org.photonvision.PhotonCamera;

public class POMObjectDetectionCamera extends PhotonCamera {
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
        return new POMDetectedObjectGroup(super.getLatestResult()
                , sortingOrder
                , configuration);
    }

    public POMDetectedObject getBestObject() {
        return getDetectionGroup().getBestObject();
    }
}


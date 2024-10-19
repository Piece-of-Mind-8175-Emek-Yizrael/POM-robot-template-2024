package frc.robot.POM_lib.Vision.ObjectDetection;

import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import java.util.ArrayList;
import java.util.List;

public class POMDetectedObjectGroup {
    List<POMDetectedObject> detectedObjects;
    ObjectSortingOrder sortingOrder = ObjectSortingOrder.Y_Closest;
    POMObjectDetectionCameraConfiguration cameraConfiguration;

    public POMDetectedObjectGroup(PhotonPipelineResult input, ObjectSortingOrder sortingOrder, POMObjectDetectionCameraConfiguration cameraConfiguration) {
        this.cameraConfiguration = cameraConfiguration;
        changeSortingOrder(sortingOrder);
        detectedObjects = new ArrayList<>();
        for (PhotonTrackedTarget trackedTarget : input.getTargets()) {
            detectedObjects.add(new POMDetectedObject(trackedTarget));
        }
    }

    public void changeSortingOrder(ObjectSortingOrder newOrder) {
        this.sortingOrder = newOrder;
    }

    public POMDetectedObject getBestObject() {
        return sortingOrder.getSorted(detectedObjects, cameraConfiguration).get(0);
    }

    public enum ObjectSortingOrder {
        Y_Closest, X_Closest, Y_Furthest, X_Furthest, X_Left_To_Right, X_Right_To_Left;


        public List<POMDetectedObject> getSorted(List<POMDetectedObject> objectList, POMObjectDetectionCameraConfiguration cameraConfiguration) {
            switch (this) {
                case X_Closest -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerX * 2 % cameraConfiguration.getWidth() >
                                    objectList.get(i + 1).centerX * 2 % cameraConfiguration.getWidth()) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                                sorted = false;
                            }
                        }
                    }
                    return objectList;
                }
                case Y_Closest -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerY > objectList.get(i + 1).centerY) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                            }
                        }
                    }
                    return objectList;
                }
                case Y_Furthest -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerY < objectList.get(i + 1).centerY) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                            }
                        }
                    }
                    return objectList;
                }
                case X_Furthest -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerX * 2 % cameraConfiguration.getWidth() <
                                    objectList.get(i + 1).centerX * 2 % cameraConfiguration.getWidth()) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                                sorted = false;
                            }
                        }
                    }
                    return objectList;
                }
                case X_Left_To_Right -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerX > objectList.get(i + 1).centerX) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                                sorted = false;
                            }
                        }
                    }
                }
                case X_Right_To_Left -> {
                    boolean sorted = false;
                    while (!sorted) {
                        sorted = true;
                        for (int i = 0; i < objectList.size() - 1; i++) {
                            if (objectList.get(i).centerX < objectList.get(i + 1).centerX) {
                                POMDetectedObject detectedObject = objectList.get(i);
                                objectList.set(i, objectList.get(i + 1));
                                objectList.set(i + 1, detectedObject);
                                sorted = false;
                            }
                        }
                    }
                }
                default -> {
                    return objectList;
                }
            }
        }
    }
}

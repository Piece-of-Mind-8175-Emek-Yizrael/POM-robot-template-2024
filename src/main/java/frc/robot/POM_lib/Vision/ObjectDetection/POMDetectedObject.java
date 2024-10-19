package frc.robot.POM_lib.Vision.ObjectDetection;

import org.photonvision.targeting.PhotonTrackedTarget;
import org.photonvision.targeting.TargetCorner;

public class POMDetectedObject {
    protected int area, centerX, centerY, width, height;
    ObjectDetectionConstants.GameObjects type;

    public POMDetectedObject(PhotonTrackedTarget target) {
        type = ObjectDetectionConstants.GameObjects.getFromId(target.getFiducialId());
        area = (int) target.getArea();
        TargetCorner[] corners = organizeCorners((TargetCorner[]) target.getDetectedCorners().toArray());
        width = (int) (corners[1].x - corners[0].x);
        height = (int) (corners[2].y - corners[1].y);
        centerX = (int) (corners[1].x + (double) (width / 2));
        centerY = (int) (corners[2].y + (double) (height / 2));
    }

    public int getArea() {
        return area;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ObjectDetectionConstants.GameObjects getType() {
        return type;
    }

    TargetCorner[] organizeCorners(TargetCorner[] orgCorners) {
        TargetCorner[] result = orgCorners;
        boolean isOrganized = false;

        while (!isOrganized) {
            isOrganized = true;
            for (int i = 0; i < result.length - 1; i++) {
                if (i == 0 && (result[i].x > result[i + 1].x)) {
                    TargetCorner object = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = object;
                    isOrganized = false;
                } else if (i == 1 && result[i].y < result[i + 1].y) {
                    TargetCorner object = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = object;
                    isOrganized = false;
                } else if (result[i].x < result[i + 1].x) {
                    TargetCorner object = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = object;
                    isOrganized = false;
                }
            }
        }

        return result;
    }
}

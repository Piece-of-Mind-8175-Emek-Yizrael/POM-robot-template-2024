package frc.robot.POM_lib.Vision.ObjectDetection;

public class POMObjectDetectionCameraConfiguration {
    int width, height;

    public POMObjectDetectionCameraConfiguration(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

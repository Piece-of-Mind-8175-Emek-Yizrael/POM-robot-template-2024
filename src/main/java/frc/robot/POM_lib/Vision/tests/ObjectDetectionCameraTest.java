package frc.robot.POM_lib.Vision.tests;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.POM_lib.Vision.ObjectDetection.POMDetectedObject;
import frc.robot.POM_lib.Vision.ObjectDetection.POMObjectDetectionCamera;

public class ObjectDetectionCameraTest extends Command {
    POMObjectDetectionCamera detectionCamera;

    public ObjectDetectionCameraTest(POMObjectDetectionCamera detectionCamera) {
        this.detectionCamera = detectionCamera;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        POMDetectedObject[] detectedObjectList = detectionCamera.getDetectionGroup().getAllObjects();
        for (POMDetectedObject object : detectedObjectList) {
            SmartDashboard.putNumber("vision/object center Y " + object.getType(), object.getCenterY());
            SmartDashboard.putNumber("vision/object center X " + object.getType(), object.getCenterX());
        }
    }

    @Override
    public void end(boolean interrupted) {
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

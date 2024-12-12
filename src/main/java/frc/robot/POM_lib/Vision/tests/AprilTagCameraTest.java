package frc.robot.POM_lib.Vision.tests;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTag;
import frc.robot.POM_lib.Vision.AprilTag.POMAprilTagCamera;

import java.io.IOException;
import java.util.List;

public class AprilTagCameraTest extends Command {
    POMAprilTagCamera camera;

    public AprilTagCameraTest(POMAprilTagCamera camera) {
        this.camera = camera;
    }

    @Override
    public void execute() {
        List<POMAprilTag> detectedObjectList = null;
        try {
            detectedObjectList = camera.getListOfVisibleTags();
            for (POMAprilTag tag : detectedObjectList) {
                SmartDashboard.putString("vision/tag to camera " + tag.getId(), tag.getCameraToTag().toString());
            }
        } catch (IOException e) {
            System.out.println("can not get tags from the camera :(");
        }

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

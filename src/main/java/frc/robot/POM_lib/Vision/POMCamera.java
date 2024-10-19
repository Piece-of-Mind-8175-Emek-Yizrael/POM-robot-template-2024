package frc.robot.POM_lib.Vision;

import org.photonvision.PhotonCamera;

public class POMCamera {
    protected PhotonCamera photonCamera;

    public POMCamera(String name) {
        photonCamera = new PhotonCamera(name);
    }

    public boolean hasTargets() {
        return photonCamera.getLatestResult().hasTargets();
    }
}

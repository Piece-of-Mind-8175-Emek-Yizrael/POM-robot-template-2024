package frc.robot.POM_lib.Vision.ObjectDetection;

public class ObjectDetectionConstants {
    enum GameObjects {
        NOTE(0, 1);
        int id, pixelToMeter;

        GameObjects(int id, int pixelToMeter) {
            this.id = id;
            this.pixelToMeter = pixelToMeter;
        }

        public static GameObjects getFromId(int num) {
            switch (num) {
                case 0:
                    return NOTE;
                default:
                    return NOTE;
            }
        }

        public int getId() {
            return id;
        }

        public double getDistFromWidth(int width) {
            return width * pixelToMeter;
        }
    }
}
